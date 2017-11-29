package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.entity.Reminders;
import edu.matc.movieCalendar.entity.User;
import edu.matc.movieCalendar.persistence.UserDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(
        name = "deleteReminder",
        urlPatterns = {"/deleteReminder"}
)
public class DeleteReminderServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Delete servlet running!");

        int movieId = Integer.parseInt(req.getParameter("movie_id"));

        UserDao userDao = new UserDao();
        User user = userDao.getUser(req.getRemoteUser());
        Set<Reminders> reminders = user.getReminders();
        Reminders deleteReminder = new Reminders();

        for (Reminders reminder : reminders) {
            if (reminder.getMovieId() == movieId) {
                deleteReminder = reminder;
                break;
            }
        }

        try {
            reminders.remove(deleteReminder);
            user.setReminders(reminders);
            userDao.updateUser(user);
        } catch (HibernateException he) {
            log.error("Error deleting movie id: " + movieId, he);
        }
    }
}
