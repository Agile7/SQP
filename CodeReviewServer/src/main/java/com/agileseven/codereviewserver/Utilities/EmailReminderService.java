/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agileseven.codereviewserver.Utilities;

import com.agileseven.codereviewserver.DAO.AccountDAO;
import com.agileseven.codereviewserver.DAO.AccountDAOImpl;
import com.agileseven.codereviewserver.DAO.CodeDAO;
import com.agileseven.codereviewserver.DAO.CodeDAOImpl;
import com.agileseven.codereviewserver.DAO.ProjectDAO;
import com.agileseven.codereviewserver.DAO.ProjectDAOImpl;
import com.agileseven.codereviewserver.DAO.UserStoryDAO;
import com.agileseven.codereviewserver.DAO.UserStoryDAOImpl;
import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.ProjectDTO;
import com.agileseven.codereviewserver.DTO.UserDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
    

/**
 *
 * @author Vu Dinh Dieu
 */
public class EmailReminderService extends BaseReminderService {
    
    private static final String SENDER_EMAIL_ADDRESS = "contact.agileseven@gmail.com";
    private static final String SENDER_EMAIL_PASSWORD = "Agile7@2018";

    private static Session mailSession;

    private static ProjectDAO projectDAO = new ProjectDAOImpl();
    private static AccountDAO accountDAO = new AccountDAOImpl();
    private static UserStoryDAO userStoryDAO = new UserStoryDAOImpl();
    private static CodeDAO codeDAO = new CodeDAOImpl();

    public EmailReminderService(ProjectDTO project) {
        super(project);
        setMailServerProperties();
    }

    @Override
    public void sendReminder(ProjectDTO project){
        try {
            String emailHost = "smtp.gmail.com";
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(emailHost, SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);

            //UserDTO source = accountDAO.getUserById(super.getReminderSource().getUserId());
            //ProjectDTO project = projectDAO.getProjectById(source.getProjectId());
            ArrayList<CodeDTO> listUnreadCode = codeDAO.getUnreadCodes(project.getProjectId());
            System.out.println("sendReminder:" + "|" + project.getProjectName() + "|" + listUnreadCode.size());
            for (UserDTO recipient: super.getReminderRecipients()) {
                System.out.println("recipient:" + "|" + recipient.getFirstName()+ "|" + recipient.getUserId());
                MimeMessage emailMessage = draftEmailMessage(project, recipient, listUnreadCode);
                transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
            }

            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static void setMailServerProperties() {
        Properties emailProperties = System.getProperties();
        emailProperties.put("mail.smtp.port", "587");
        emailProperties.put("mail.smtp.auth", "true");
        emailProperties.put("mail.smtp.starttls.enable", "true");
        mailSession = Session.getInstance(emailProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
            }
        });
    }
    
    private MimeMessage draftEmailMessage(ProjectDTO project, UserDTO recipient, ArrayList<CodeDTO> listUnreadCode) throws MessagingException, SQLException, ClassNotFoundException {
        String emailSubject = "CODE REVIEW REMINDER";
        String emailBody = "Dear " + recipient.getFirstName() + " " + recipient.getLastName().toUpperCase() + ",\n\n" +
                listUnreadCode.size() + "code pieces from the project: " + project.getProjectName() + 
                " need to be reviewed. Please do not forget to review it as soon as possible.\n\n" +
                "Have a nice day!\n\n" +
                "--------------------\nThis is an auto-generated message, sent using the AgileSeven code review platform.";

        MimeMessage emailMessage = new MimeMessage(mailSession);

        emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.getEmail()));

        emailMessage.setSubject(emailSubject);
        emailMessage.setText(emailBody);

        return emailMessage;
    }
    
}
