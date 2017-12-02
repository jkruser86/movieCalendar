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
import javax.servlet.http.HttpSession;
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

        int movieId = Integer.parseInt(req.getParameter("movie_id"));

        UserDao userDao = new UserDao();
        User user = new User();
        Set<Reminders> reminders = null;
        HttpSession session = req.getSession();
        String userName = req.getRemoteUser();

        try {
            user = userDao.getUser(userName);
            reminders = user.getReminders();
        } catch (HibernateException he) {
            log.error("Hibernate Exception gathering reminders for user " + userName, he);
            session.setAttribute("error", "Error gathering reminders");
            resp.sendRedirect("errorPage");
        } catch (Exception e) {
            log.error("Exception gathering reminders for user " + userName, e);
            session.setAttribute("error", "Error gathering reminders");
            resp.sendRedirect("errorPage");
        }

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
            log.error("HibernateException deleting movie id: " + movieId + " for user " + userName, he);
            session.setAttribute("error", "Error deleting reminder");
            resp.sendRedirect("errorPage");
        } catch (Exception e) {
            log.error("Exception deleting movie id: " + movieId + " for user " + userName, e);
            session.setAttribute("error", "Error deleting reminder");
            resp.sendRedirect("errorPage");
        }
    }
}
