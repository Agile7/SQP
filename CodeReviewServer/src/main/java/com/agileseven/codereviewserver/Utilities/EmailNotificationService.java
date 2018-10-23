package com.agileseven.codereviewserver.Utilities;

import com.agileseven.codereviewserver.DAO.*;
import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.ProjectDTO;
import com.agileseven.codereviewserver.DTO.UserDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Mahmoud AL NAJAR
 *
 */
public class EmailNotificationService extends BaseNotificationService {

    private static final String SENDER_EMAIL_ADDRESS = "contact.agileseven@gmail.com";
    private static final String SENDER_EMAIL_PASSWORD = "Agile7@2018";

    private static Session mailSession;

    private static ProjectDAO projectDAO = new ProjectDAOImpl();
    private static AccountDAO accountDAO = new AccountDAOImpl();
    private static UserStoryDAO userStoryDAO = new UserStoryDAOImpl();

    public EmailNotificationService(CodeDTO notificationSource) {
        super(notificationSource);
        setMailServerProperties();
    }

    @Override
    public void sendNotification() {

        try {
            String emailHost = "smtp.gmail.com";
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(emailHost, SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);

            UserDTO source = accountDAO.getUserById(super.getNotificationSource().getUserId());
            ProjectDTO project = projectDAO.getProjectById(source.getProjectId());
            for (UserDTO recipient: super.getNotificationRecipients()) {
                MimeMessage emailMessage = draftEmailMessage(project, source, recipient);
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
        mailSession = Session.getDefaultInstance(emailProperties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);
            }
        });
    }

    private MimeMessage draftEmailMessage() throws MessagingException {
        List<String> toEmails = new ArrayList<>();
        for (UserDTO user : super.getNotificationRecipients()) {
            toEmails.add(user.getEmail());
        }
        String emailSubject = "New code ready to be reviewed";
        String emailBody = "code uploaded by userId: " + super.getNotificationSource().getUserId();

        MimeMessage emailMessage = new MimeMessage(mailSession);

        for (String toEmail : toEmails) {
            emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
        }

        emailMessage.setSubject(emailSubject);
        emailMessage.setText(emailBody);

        return emailMessage;
    }

    private MimeMessage draftEmailMessage(ProjectDTO project, UserDTO source, UserDTO recipient) throws MessagingException, SQLException, ClassNotFoundException {
        String emailSubject = "New code ready to be reviewed";
        String emailBody = "Dear " + recipient.getFirstName() + " " + recipient.getLastName().toUpperCase() + ",\n\n" +
                "New code from the project: " + project.getProjectName() +
                ", user story: " + userStoryDAO.getAllUserStories().stream().filter(
                        us -> us.getUserstoryId().equals(super.getNotificationSource().getUserStoryId())
                        ).findFirst().get().getTitle() +
                " was just pushed by " + source.getFirstName() + " " + source.getLastName().toUpperCase() +
                ". Please do not forget to review it as soon as possible.\n\n" +
                "Have a nice day!\n\n" +
                "--------------------\nThis is an auto-generated message, sent using the AgileSeven code review platform.";

        MimeMessage emailMessage = new MimeMessage(mailSession);

        emailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient.getEmail()));

        emailMessage.setSubject(emailSubject);
        emailMessage.setText(emailBody);

        return emailMessage;
    }

}
