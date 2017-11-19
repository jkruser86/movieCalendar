package edu.matc.movieCalendar.util;

import edu.matc.movieCalendar.entity.Reminders;
import edu.matc.movieCalendar.persistence.RemindersDao;
import org.apache.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

        RemindersDao remindersDao = new RemindersDao();

        List<Reminders> reminders = remindersDao.getAllReminders();

        allRemindersLoop(reminders);
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

    public void allRemindersLoop(List<Reminders> allReminders) {

        for (Reminders reminder: allReminders) {
            MovieApiCalls movieApiCalls = new MovieApiCalls();
            Movie movie = new Movie();

            try {
                movie = movieApiCalls.getMovieInfo(reminder.getMovieId());
            } catch (IOException io) {
                log.error("Error getting all reminders", io);
            }

            if (movie.getTheatricalRelease() == LocalDate.now().minusDays(reminder.getTheaterDaysBefore())) {
                //TODO: Send email for theatrical release
            }

            if (movie.getDigitalRelease() == LocalDate.now().minusDays(reminder.getDigitalDaysBefore())) {
                //TODO: Send email for digital release
            }

            if (movie.getPhysicalRelease() == LocalDate.now().minusDays(reminder.getPhysicalDaysBefore())) {
                //TODO: Send email for physical release
            }
        }
    }
}
