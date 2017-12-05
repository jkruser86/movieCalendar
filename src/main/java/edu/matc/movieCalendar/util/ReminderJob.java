package edu.matc.movieCalendar.util;

import edu.matc.movieCalendar.entity.Reminders;
import edu.matc.movieCalendar.entity.User;
import edu.matc.movieCalendar.persistence.UserDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
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

/**
 * This is the Quartz job that is run on a schedule. It checks to see if any user has a reminder ready to send and
 * sends an email to any that are ready.
 *
 * @author Jamie Kruser
 */
public class ReminderJob implements org.quartz.Job {

    private final Logger log = Logger.getLogger(this.getClass());
    private final String username = "moviecalendarapp@gmail.com";
    private final String password = "movieCalendar";

    private Properties properties = new Properties();

    /**
     * Generic class constructor that sets up the email properties
     */
    public ReminderJob() {
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        //properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    }

    /**
     * Executes the Quartz execution for the schedule. Checks and sends reminders to users
     *
     * @param context the context for the Quartz job execution
     * @throws JobExecutionException handles the exception from the Quartz job execution
     */
    public void execute(JobExecutionContext context) throws JobExecutionException {
        //log.info("ReminderJob is executing");

        UserDao userDao = new UserDao();
        List<User> users = new ArrayList<User>();
        try {
            users = userDao.getAllUsers();
            allRemindersLoop(users);
        } catch (HibernateException he) {
            log.error("Hibernate Exception gathering all errors", he);
        }

    }

    /**
     * Loops through the list of reminders for any that are ready to be sent
     *
     * @param allUsers a list of reminders to search for
     */
    public void allRemindersLoop(List<User> allUsers) {

        for (User user: allUsers) {
            for (Reminders reminder: user.getReminders()) {
                MovieApiCalls movieApiCalls = new MovieApiCalls();
                Movie movie = new Movie();

                try {
                    movie = movieApiCalls.getMovieInfo(reminder.getMovieId());
                } catch (IOException io) {
                    log.error("Error getting all reminders for user " + user.getUserName(), io);
                }

                checkReminder(movie, reminder);
            }
        }
    }

    /**
     * Checks to see if the reminder is ready to be sent to the user
     *
     * @param movie the movie the user wants to be reminded about
     * @param reminder the reminder that the user has setup
     */
    public void checkReminder(Movie movie, Reminders reminder) {

        if (reminder.getTheaterDaysBefore() >= 0 && movie.getTheatricalRelease() != null) {
            if (LocalDate.now().equals(movie.getTheatricalRelease().minusDays(reminder.getTheaterDaysBefore()))) {

                String message = "This is a friendly reminder from MovieCalendar that the movie " + movie.getTitle()
                        + " is coming to theaters on " + movie.getTheatricalRelease();

                sendReminder(reminder.getUser().getUserName(), message);
            }
        }

        if (reminder.getDigitalDaysBefore() >= 0 && movie.getDigitalRelease() != null) {
            if (LocalDate.now().equals(movie.getDigitalRelease().minusDays(reminder.getDigitalDaysBefore()))) {

                String message = "This is a friendly reminder from MovieCalendar that the movie " + movie.getTitle()
                        + " is coming out on digital on " + movie.getDigitalRelease();
                sendReminder(reminder.getUser().getUserName(), message);
            }

        }

        if (reminder.getPhysicalDaysBefore() >= 0 && movie.getPhysicalRelease() != null) {
            if (LocalDate.now().equals(movie.getPhysicalRelease().minusDays(reminder.getPhysicalDaysBefore()))) {

                String message = "This is a friendly reminder from MovieCalendar that the movie " + movie.getTitle()
                        + " is coming out on physical on " + movie.getPhysicalRelease();
                sendReminder(reminder.getUser().getUserName(), message);
            }
        }
    }

    /**
     * Sends the email reminder to the user
     *
     * @param inputUsername the username for the reminder
     * @param emailMessage the message to send in the email
     */
    public void sendReminder(String inputUsername, String emailMessage) {

        String userEmail = "";

        try {
            userEmail = getEmailAddress(inputUsername);
        } catch (HibernateException he) {
            log.error("Hibernate Exception gathering email address for user " + inputUsername, he);
        }

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
            message.setText("Dear " + inputUsername + ", \n\n " + emailMessage);

            Transport.send(message);
        } catch (MessagingException me) {
            log.error("Error sending email for user " + inputUsername, me);
        }
    }

    /**
     * Gets the email address for the user
     *
     * @param username the username to use to find the email address
     * @return the users email address
     */
    public String getEmailAddress(String username) throws HibernateException {
        UserDao userDao = new UserDao();
        User user = userDao.getUser(username);

        return user.getUserEmail();
    }
}
