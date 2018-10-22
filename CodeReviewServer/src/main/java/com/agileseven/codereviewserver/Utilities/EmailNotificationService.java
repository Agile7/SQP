package com.agileseven.codereviewserver.Utilities;

import com.agileseven.codereviewserver.DTO.CodeDTO;
import com.agileseven.codereviewserver.DTO.UserDTO;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Mahmoud AL NAJAR
 * TODO: MODIFY THE EMAILS IN DB BEFORE TESTING
 */
public class EmailNotificationService extends BaseNotificationService {

    private static final String SENDER_EMAIL_ADDRESS = "contact.agileseven@gmail.com";
    private static final String SENDER_EMAIL_PASSWORD = "Agile7@2018";

    private static Session mailSession;

    public EmailNotificationService(CodeDTO notificationSource) {
        super(notificationSource);
        setMailServerProperties();
    }

    @Override
    public void sendNotification() {

        try {
//            List<UserDTO> recipients = super.getNotificationRecipients();
            String emailHost = "smtp.gmail.com";
            Transport transport = mailSession.getTransport("smtp");
            transport.connect(emailHost, SENDER_EMAIL_ADDRESS, SENDER_EMAIL_PASSWORD);

            MimeMessage emailMessage = draftEmailMessage();

            transport.sendMessage(emailMessage, emailMessage.getAllRecipients());
            transport.close();
        } catch (MessagingException e) {
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

        // TODO: MODIFY THE EMAILS IN DB BEFORE TESTING
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

//    private void sendEmail() throws MessagingException {
//
//    }

}
