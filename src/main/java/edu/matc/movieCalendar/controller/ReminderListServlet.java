package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.entity.Reminders;
import edu.matc.movieCalendar.entity.User;
import edu.matc.movieCalendar.persistence.UserDao;
import edu.matc.movieCalendar.util.Movie;
import edu.matc.movieCalendar.util.MovieApiCalls;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The servlet that handles the processing of the user's reminders list
 *
 * @author Jamie Kruser
 */
@WebServlet(
        name = "reminderList",
        urlPatterns = {"/reminderList"}
)
public class ReminderListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        MovieApiCalls movieApiCalls = new MovieApiCalls();
        String userName = req.getRemoteUser();
        List<Movie> movies = new ArrayList<Movie>();

        UserDao userDao = new UserDao();
        User user = userDao.getUser(userName);

        for (Reminders reminders: user.getReminders()) {
            movies.add(movieApiCalls.getMovieInfo(reminders.getMovieId()));
        }

        req.setAttribute("movies", movies);
        String url = "/reminder-list.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(req, resp);
    }
}
