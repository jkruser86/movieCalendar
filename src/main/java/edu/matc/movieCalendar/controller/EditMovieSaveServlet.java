package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.entity.Reminders;
import edu.matc.movieCalendar.entity.User;
import edu.matc.movieCalendar.persistence.UserDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Set;

/**
 * The servlet that handles the editing of currently saved movie reminders
 *
 * @author Jamie Kruser
 */
@WebServlet(
        name = "editSave",
        urlPatterns = {"/editSave"}
)
public class EditMovieSaveServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    private User user;
    private UserDao userDao;
    private Set<Reminders> reminders;
    private Reminders updateReminder;
    private String theaterNumber;
    private String theaterTimeframe;
    private String digitalNumber;
    private String digitalTimeframe;
    private String physicalNumber;
    private String physicalTimeframe;

    /**
     * Handles the server post for the edit saved movie reminders
     *
     * @param req the request from the server
     * @param resp the response from the server
     * @throws ServletException general servlet exception
     * @throws IOException general IO exception
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        userDao = new UserDao();
        user = new User();
        updateReminder = new Reminders();
        HttpSession session = req.getSession();
        String userName = req.getRemoteUser();
        String redirectCheck;

        int movieId = Integer.parseInt(req.getParameter("movie_id"));
        theaterNumber = req.getParameter("theater_number");
        theaterTimeframe = req.getParameter("theater_timeframe");
        digitalNumber = req.getParameter("digital_number");
        digitalTimeframe = req.getParameter("digital_timeframe");
        physicalNumber = req.getParameter("physical_number");
        physicalTimeframe = req.getParameter("physical_timeframe");

        redirectCheck = getUser(session, userName);

        if (redirectCheck.equals("")) {
            reminders = user.getReminders();
            updateReminder = getUpdateReminder(movieId);

            if (updateReminder == null) {
                log.error("Error finding reminder for movie " + movieId);
                session.setAttribute("error", "Error finding reminder");
                String url = "errorPage";
                resp.sendRedirect(url);
            } else {
                reminders.remove(updateReminder);

                setupReminder();

                updateReminder.setUser(user);
                user.getReminders().add(updateReminder);

                redirectCheck = updateUser(session);

                if (redirectCheck.equals("")) {
                    session.setAttribute("update", true);
                } else {
                    resp.sendRedirect(redirectCheck);
                }
            }
        } else {
            resp.sendRedirect(redirectCheck);
        }
    }

    /**
     * Gets the current user from the database
     *
     * @param session the servers session
     * @param userName the user name of the current user
     * @return the error site if an error occurs
     */
    protected String getUser(HttpSession session, String userName) {
        try {
            user = userDao.getUser(userName);
            return "";
        } catch (HibernateException he) {
            log.error("Hibernate Exception gathering user " + userName, he);
            session.setAttribute("error", "Error gathering user to update reminders");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception gathering user " + userName, e);
            session.setAttribute("error", "Error gathering user to update reminders");
            return "errorPage";
        }
    }

    /**
     * Finds the specific reminder from all the users reminders
     *
     * @param movieId the id for the movie to update
     * @return the reminder if one is found or null if not
     */
    private Reminders getUpdateReminder(int movieId) {
        for (Reminders reminder : reminders) {
            if (reminder.getMovieId() == movieId) {
                return reminder;
            }
        }

        return null;
    }

    /**
     * Sets up the reminder for whatever timeframe the user has set
     */
    private void setupReminder() {
        //Add theater reminder
        if (theaterNumber.equals("")) {
            updateReminder.setTheaterDaysBefore(-1);
        } else {
            updateReminder.setTheaterDaysBefore(calculateDays(theaterTimeframe, theaterNumber));
        }

        //Add digital reminder
        if (digitalNumber.equals("")) {
            updateReminder.setDigitalDaysBefore(-1);
        } else {
            updateReminder.setDigitalDaysBefore(calculateDays(digitalTimeframe, digitalNumber));
        }

        //Add physical reminder
        if (physicalNumber.equals("")) {
            updateReminder.setPhysicalDaysBefore(-1);
        } else {
            updateReminder.setPhysicalDaysBefore(calculateDays(physicalTimeframe, physicalNumber));
        }
    }

    /**
     * Calculates the number of days for the setup the user selected
     *
     * @param timeframe the timeframe the user wants to be reminded (Day, Week, Month)
     * @param number the number of given timeframe to be reminded
     * @return the number of days for the given timeframe and number
     */
    private int calculateDays(String timeframe, String number) {
        if (timeframe.equals("Day")) {
            return Integer.parseInt(number);
        } else if (timeframe.equals("Week")) {
            return Integer.parseInt(number) * 7;
        } else {
            return Integer.parseInt(number) * 30;
        }
    }

    /**
     * Updates the user with the newly updated reminder
     *
     * @param session the session of the servlet
     * @return the error site if an error occurs
     */
    private String updateUser(HttpSession session) {
        try {
            userDao.updateUser(user);
            return "";
        } catch (HibernateException he) {
            log.error("Hibernate Exception while updating reminder for user " + user.getUserName(), he);
            session.setAttribute("error", "Error updating reminder");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception while updating reminder for user " + user.getUserName(), e);
            session.setAttribute("error", "Error updating reminder");
            return "errorPage";
        }
    }
}
