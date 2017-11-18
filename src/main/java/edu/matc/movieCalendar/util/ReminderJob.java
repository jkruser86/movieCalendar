package edu.matc.movieCalendar.util;

import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ReminderJob implements org.quartz.Job {

    private final Logger log = Logger.getLogger(this.getClass());
    private final String username = "moviecalendarapp@gmail.com";
    private final String password = "movieCalendar";
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //log.info("ReminderJob is executing");

        System.out.println("ReminderJob is executing");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("jamie.kruser@gmail.com"));
            message.setSubject("Test");
            message.setText("Dear Client," + "\n\n Test message");

            Transport.send(message);

            System.out.println("Email Sent");
        } catch (MessagingException me) {
            log.error("Error sending email", me);
        }
    }
}
