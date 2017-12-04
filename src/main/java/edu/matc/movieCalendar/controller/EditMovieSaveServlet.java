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

//TODO: Possibly add successful message to edit page
//TODO: Add javadoc
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

        if (redirectCheck == "") {
            reminders = user.getReminders();
            updateReminder = getUpdateReminder(movieId);

            if (updateReminder == null) {
                log.error("Error finding reminder for movie " + movieId);
                session.setAttribute("error", "Error finding reminder");
                String url = "errorPage";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

                dispatcher.forward(req, resp);
            } else {
                reminders.remove(updateReminder);

                setupReminder();

                updateReminder.setUser(user);
                user.getReminders().add(updateReminder);

                redirectCheck = updateUser(session);

                if (redirectCheck == "") {
                    session.setAttribute("update", true);
                } else {
                    String url = redirectCheck;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

                    dispatcher.forward(req, resp);
                }
            }
        } else {
            String url = redirectCheck;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

            dispatcher.forward(req, resp);
        }
    }

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

    protected Reminders getUpdateReminder(int movieId) {
        for (Reminders reminder : reminders) {
            if (reminder.getMovieId() == movieId) {
                return reminder;
            }
        }

        return null;
    }

    protected void setupReminder() {
        //Add theater reminder
        if (theaterNumber == "") {
            updateReminder.setTheaterDaysBefore(-1);
        } else {
            updateReminder.setTheaterDaysBefore(calculateDays(theaterTimeframe, theaterNumber));
        }

        //Add digital reminder
        if (digitalNumber == "") {
            updateReminder.setDigitalDaysBefore(-1);
        } else {
            updateReminder.setDigitalDaysBefore(calculateDays(digitalTimeframe, digitalNumber));
        }

        //Add physical reminder
        if (physicalNumber == "") {
            updateReminder.setPhysicalDaysBefore(-1);
        } else {
            updateReminder.setPhysicalDaysBefore(calculateDays(physicalTimeframe, physicalNumber));
        }
    }

    protected int calculateDays(String timeframe, String number) {
        if (timeframe.equals("Day")) {
            return Integer.parseInt(number);
        } else if (timeframe.equals("Week")) {
            return Integer.parseInt(number) * 7;
        } else {
            return Integer.parseInt(number) * 30;
        }
    }

    protected String updateUser(HttpSession session) {
        try {
            userDao.updateUser(user);
            return "";
        } catch (HibernateException he) {
            log.error("Error while saving reminder for user " + user.getUserName(), he);
            session.setAttribute("error", "Error updating reminder");
            return "errorPage";
        }
    }
}
