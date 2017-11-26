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

        Reminders reminders = new Reminders();
        int movieId = Integer.parseInt(req.getParameter("movie_id"));
        String theaterNumber = req.getParameter("theater_number");
        String theaterTimeframe = req.getParameter("theater_timeframe");
        String digitalNumber = req.getParameter("digital_number");
        String digitalTimeframe = req.getParameter("digital_timeframe");
        String physicalNumber = req.getParameter("physical_number");
        String physicalTimeframe = req.getParameter("physical_timeframe");

        String userName = req.getRemoteUser();

        reminders.setMovieId(movieId);

        //Add theater reminder
        if (theaterNumber == "") {
            reminders.setTheaterDaysBefore(-1);
        } else {
            if (theaterTimeframe.equals("Day")) {
                reminders.setTheaterDaysBefore(Integer.parseInt(theaterNumber));
            } else if (theaterTimeframe.equals("Week")) {
                reminders.setTheaterDaysBefore(Integer.parseInt(theaterNumber) * 7);
            } else {
                reminders.setTheaterDaysBefore(Integer.parseInt(theaterNumber) * 30);
            }
        }

        //Add digital reminder
        if (digitalNumber == "") {
            reminders.setDigitalDaysBefore(-1);
        } else {
            if (digitalTimeframe.equals("Day")) {
                reminders.setDigitalDaysBefore(Integer.parseInt(digitalNumber));
            } else if (digitalTimeframe.equals("Week")) {
                reminders.setDigitalDaysBefore(Integer.parseInt(digitalNumber) * 7);
            } else {
                reminders.setDigitalDaysBefore(Integer.parseInt(digitalNumber) * 30);
            }
        }

        //Add physical reminder
        if (physicalNumber == "") {
            reminders.setPhysicalDaysBefore(-1);
        } else {
            if (physicalTimeframe.equals("Day")) {
                reminders.setPhysicalDaysBefore(Integer.parseInt(physicalNumber));
            } else if (physicalTimeframe.equals("Week")) {
                reminders.setPhysicalDaysBefore(Integer.parseInt(physicalNumber) * 7);
            } else {
                reminders.setPhysicalDaysBefore(Integer.parseInt(physicalNumber) * 30);
            }
        }

        try {
            UserDao userDao = new UserDao();
            User user = userDao.getUser(userName);
            reminders.setUser(user);
            user.getReminders().add(reminders);
            userDao.updateUser(user);
        } catch (HibernateException he) {
            log.error("Error while saving reminder", he);
        }
    }
}
