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

//TODO: Need to add error handling
//TODO: Add javadoc
@WebServlet(
        name = "editSave",
        urlPatterns = {"/editSave"}
)
public class EditMovieSaveServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int movieId = Integer.parseInt(req.getParameter("movie_id"));
        String theaterNumber = req.getParameter("theater_number");
        String theaterTimeframe = req.getParameter("theater_timeframe");
        String digitalNumber = req.getParameter("digital_number");
        String digitalTimeframe = req.getParameter("digital_timeframe");
        String physicalNumber = req.getParameter("physical_number");
        String physicalTimeframe = req.getParameter("physical_timeframe");

        UserDao userDao = new UserDao();
        User user = userDao.getUser(req.getRemoteUser());
        Set<Reminders> reminders = user.getReminders();
        Reminders updateReminder = new Reminders();

        for (Reminders reminder : reminders) {
            if (reminder.getMovieId() == movieId) {
                updateReminder = reminder;
                break;
            }
        }

        reminders.remove(updateReminder);

        //Add theater reminder
        if (theaterNumber == "") {
            updateReminder.setTheaterDaysBefore(-1);
        } else {
            if (theaterTimeframe.equals("Day")) {
                updateReminder.setTheaterDaysBefore(Integer.parseInt(theaterNumber));
            } else if (theaterTimeframe.equals("Week")) {
                updateReminder.setTheaterDaysBefore(Integer.parseInt(theaterNumber) * 7);
            } else {
                updateReminder.setTheaterDaysBefore(Integer.parseInt(theaterNumber) * 30);
            }
        }

        //Add digital reminder
        if (digitalNumber == "") {
            updateReminder.setDigitalDaysBefore(-1);
        } else {
            if (digitalTimeframe.equals("Day")) {
                updateReminder.setDigitalDaysBefore(Integer.parseInt(digitalNumber));
            } else if (digitalTimeframe.equals("Week")) {
                updateReminder.setDigitalDaysBefore(Integer.parseInt(digitalNumber) * 7);
            } else {
                updateReminder.setDigitalDaysBefore(Integer.parseInt(digitalNumber) * 30);
            }
        }

        //Add physical reminder
        if (physicalNumber == "") {
            updateReminder.setPhysicalDaysBefore(-1);
        } else {
            if (physicalTimeframe.equals("Day")) {
                updateReminder.setPhysicalDaysBefore(Integer.parseInt(physicalNumber));
            } else if (physicalTimeframe.equals("Week")) {
                updateReminder.setPhysicalDaysBefore(Integer.parseInt(physicalNumber) * 7);
            } else {
                updateReminder.setPhysicalDaysBefore(Integer.parseInt(physicalNumber) * 30);
            }
        }

        try {
            updateReminder.setUser(user);
            //reminders.add(updateReminder);
            user.getReminders().add(updateReminder);
            //user.setReminders(reminders);
            userDao.updateUser(user);
        } catch (HibernateException he) {
            log.error("Error while saving reminder", he);
        }
    }
}
