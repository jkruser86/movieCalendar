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

/**
 * The servlet that handles the deletion of saved reminders for a user
 *
 * @author Jamie Kruser
 */
@WebServlet(
        name = "deleteReminder",
        urlPatterns = {"/deleteReminder"}
)
public class DeleteReminderServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    private UserDao userDao;
    private User user;
    private Set<Reminders> reminders;

    /**
     * The post for the servlet which handles the deletion of the reminder
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int movieId = Integer.parseInt(req.getParameter("movie_id"));

        userDao = new UserDao();
        user = new User();
        reminders = null;
        HttpSession session = req.getSession();
        String userName = req.getRemoteUser();
        Reminders deleteReminder = new Reminders();

        String redirectCheck;

        redirectCheck = getUser(session, userName);

        if (redirectCheck.equals("")) {
            reminders = user.getReminders();
            deleteReminder = getReminder(movieId);

            if (deleteReminder == null) {
                log.error("Reminder not found while deleting for movie " + movieId + " and user " + userName);
                session.setAttribute("error", "Error while deleting reminder");
                resp.sendRedirect("errorPage");
            } else {
                reminders.remove(deleteReminder);
                user.setReminders(reminders);
                redirectCheck = updateUser(session, userName);

                if (!redirectCheck.equals("")) {
                    resp.sendRedirect(redirectCheck);
                }
            }
        } else {
            resp.sendRedirect(redirectCheck);
        }
    }

    /**
     * Gets the user from the database
     *
     * @param session the current server session
     * @param userName the user name of the current user
     * @return the response page to forward if anything goes wrong
     */
    private String getUser(HttpSession session, String userName) {
        try {
            user = userDao.getUser(userName);
            return "";
        } catch (HibernateException he) {
            log.error("Hibernate Exception gathering reminders for user " + userName, he);
            session.setAttribute("error", "Error gathering reminders");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception gathering reminders for user " + userName, e);
            session.setAttribute("error", "Error gathering reminders");
            return "errorPage";
        }
    }

    /**
     * Loops through all reminders to find the matching movie
     *
     * @param movieId the movie to find in the reminders
     * @return the reminder for the movie
     */
    private Reminders getReminder(int movieId) {
        for (Reminders reminder : reminders) {
            if (reminder.getMovieId() == movieId) {
                return reminder;
            }
        }

        return null;
    }

    /**
     * Updates the user and their reminders to remove the deleted reminder
     *
     * @param session the current server session
     * @param userName the user name of the current user
     * @return the response page to forward if anything goes wrong
     */
    private String updateUser(HttpSession session, String userName) {
        try {
            userDao.updateUser(user);
            return "";
        } catch (HibernateException he) {
            log.error("HibernateException while updating after delete for user " + userName, he);
            session.setAttribute("error", "Error deleting reminder");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception while updating after delete for user " + userName, e);
            session.setAttribute("error", "Error deleting reminder");
            return "errorPage";
        }
    }
}
