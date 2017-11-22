package edu.matc.movieCalendar.util;

import edu.matc.movieCalendar.entity.Reminders;
import edu.matc.movieCalendar.entity.User;
import edu.matc.movieCalendar.persistence.RemindersDao;
import edu.matc.movieCalendar.persistence.UserDao;
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

    private Properties properties = new Properties();

    public ReminderJob() {
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
    }
    
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //log.info("ReminderJob is executing");

        RemindersDao remindersDao = new RemindersDao();

        List<Reminders> reminders = remindersDao.getAllReminders();

        allRemindersLoop(reminders);
        System.out.println("ReminderJob is executing");

        //properties.put("mail.smtp.auth", "true");
        //properties.put("mail.smtp.starttls.enable", "true");
        //properties.put("mail.smtp.host", "smtp.gmail.com");
        //properties.put("mail.smtp.port", "465");
        //properties.put("mail.smtp.port", "587");


        /**Session session = Session.getInstance(properties,
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
        } */
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

            checkReminder(movie, reminder);
        }
    }

    public void checkReminder(Movie movie, Reminders reminder) {

        //Test!!!
        System.out.println("movie release date: " + movie.getTheatricalRelease());
        System.out.println("number of days before: " + reminder.getTheaterDaysBefore());
        System.out.println("now minus days before: " + movie.getTheatricalRelease().minusDays(reminder.getTheaterDaysBefore()));


        if (LocalDate.now().equals(movie.getTheatricalRelease().minusDays(reminder.getTheaterDaysBefore()))) {
            //TODO: Send email for theatrical release
            String message = "This is a friendly reminder from MovieCalendar that the movie " + movie.getTitle()
                    + " is coming to theaters on " + movie.getTheatricalRelease();

            sendReminder(reminder, message);
        }

        if (LocalDate.now().equals(movie.getDigitalRelease().minusDays(reminder.getDigitalDaysBefore()))) {
            //TODO: Send email for digital release

            String message = "This is a friendly reminder from MovieCalendar that the movie " + movie.getTitle()
                    + " is coming out on digital on " + movie.getDigitalRelease();
            sendReminder(reminder, message);
        }

        if (LocalDate.now().equals(movie.getPhysicalRelease().minusDays(reminder.getPhysicalDaysBefore()))) {
            //TODO: Send email for physical release

            String message = "This is a friendly reminder from MovieCalendar that the movie " + movie.getTitle()
                    + " is coming out on physical on " + movie.getPhysicalRelease();
            sendReminder(reminder, message);
        }
    }

    public void sendReminder(Reminders reminder, String emailMessage) {

        String userEmail = getEmailAddress(reminder.getUserName());

        Session session = Session.getInstance(properties,
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
                    InternetAddress.parse(userEmail));
            message.setSubject("Movie Reminder");
            message.setText("Dear " + reminder.getUserName() + ", \n\n " + emailMessage);

            Transport.send(message);

            System.out.println("Email Sent");
        } catch (MessagingException me) {
            log.error("Error sending email", me);
        }
    }


    public String getEmailAddress(String username) {
        UserDao userDao = new UserDao();
        User user = userDao.getUser(username);

        return user.getUserEmail();
    }
}
