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

//TODO: Possibly add check for existing reminder
/**
 * This servlet handles the saving of a movie for a user
 *
 * @author Jamie Kruser
 */
@WebServlet(
        name = "movieSave",
        urlPatterns = { "/movieSave" }
)
public class MovieSaveServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    private String theaterNumber;
    private String theaterTimeframe;
    private String digitalNumber;
    private String digitalTimeframe;
    private String physicalNumber;
    private String physicalTimeframe;
    private Reminders reminders;
    private UserDao userDao;
    private User user;

    /**
     * The doPost which will save the movie for the user
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String redirectCheck;

        reminders = new Reminders();
        userDao = new UserDao();
        user = new User();
        theaterNumber = req.getParameter("theater_number");
        theaterTimeframe = req.getParameter("theater_timeframe");
        digitalNumber = req.getParameter("digital_number");
        digitalTimeframe = req.getParameter("digital_timeframe");
        physicalNumber = req.getParameter("physical_number");
        physicalTimeframe = req.getParameter("physical_timeframe");

        int movieId = Integer.parseInt(req.getParameter("movie_id"));

        String userName = req.getRemoteUser();

        reminders.setMovieId(movieId);

        setupReminder();

        redirectCheck = getUser(session, userName);

        if (redirectCheck.equals("")) {
            redirectCheck = createReminder(session, userName);
            if (!redirectCheck.equals("")) {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirectCheck);

                dispatcher.forward(req, resp);
            }
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirectCheck);

            dispatcher.forward(req, resp);
        }
    }

    /**
     * Sets up the reminder for whatever timeframe the user has set
     */
    private void setupReminder() {
        //Add theater reminder
        if (theaterNumber.equals("")) {
            reminders.setTheaterDaysBefore(-1);
        } else {
            reminders.setTheaterDaysBefore(calculateDays(theaterTimeframe, theaterNumber));
        }

        //Add digital reminder
        if (digitalNumber.equals("")) {
            reminders.setDigitalDaysBefore(-1);
        } else {
            reminders.setDigitalDaysBefore(calculateDays(digitalTimeframe, digitalNumber));
        }

        //Add physical reminder
        if (physicalNumber.equals("")) {
            reminders.setPhysicalDaysBefore(-1);
        } else {
            reminders.setPhysicalDaysBefore(calculateDays(physicalTimeframe, physicalNumber));
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
     * Gets the current user information from the database
     *
     * @param session the current server session
     * @param userName the user name for the current user
     * @return the error site if an error occurs
     */
    private String getUser(HttpSession session, String userName) {
        try {
            user = userDao.getUser(userName);
            return "";
        } catch (HibernateException he) {
            log.error("Hibernate Exception while gathering user to save reminder for user " + userName, he);
            session.setAttribute("error", "Error gathering user");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception while gathering user to save reminder for user " + userName, e);
            session.setAttribute("error", "Error gathering user");
            return "errorPage";
        }
    }

    /**
     * Creates the reminder for the movie
     *
     * @param session the current server session
     * @param userName the user name for the current user
     * @return the error site if an error occurs
     */
    private String createReminder(HttpSession session, String userName) {
        try {
            reminders.setUser(user);
            user.getReminders().add(reminders);
            userDao.updateUser(user);
            return "";
        } catch (HibernateException he) {
            log.error("Hibernate Exception while saving reminder for user " + userName, he);
            session.setAttribute("error", "Error updating reminder");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception while saving reminder for user " + userName, e);
            session.setAttribute("error", "Error updating reminder");
            return "errorPage";
        }
    }
}
