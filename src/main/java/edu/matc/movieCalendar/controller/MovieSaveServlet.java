package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.entity.Reminders;
import edu.matc.movieCalendar.persistence.RemindersDao;

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
        RemindersDao remindersDao = new RemindersDao();
        int movieId = Integer.parseInt(req.getParameter("movie_id"));
        String theaterNumber = req.getParameter("theater_number");
        String theaterTimeframe = req.getParameter("theater_timeframe");
        String digitalNumber = req.getParameter("digital_number");
        String digitalTimeframe = req.getParameter("digital_timeframe");
        String physicalNumber = req.getParameter("physical_number");
        String physicalTimeframe = req.getParameter("physical_timeframe");
        System.out.println(movieId);

        reminders.setMovieId(movieId);
        reminders.setUserName(req.getRemoteUser());

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

        remindersDao.addReminder(reminders);
    }
}
