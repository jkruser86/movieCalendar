package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.entity.Reminders;
import edu.matc.movieCalendar.entity.User;
import edu.matc.movieCalendar.persistence.UserDao;
import edu.matc.movieCalendar.util.Movie;
import edu.matc.movieCalendar.util.MovieApiCalls;
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

    private final Logger log = Logger.getLogger(this.getClass());

    private UserDao userDao;
    private User user;
    private MovieApiCalls movieApiCalls;
    private List<Movie> movies;
    private String userName;

    /**
     * The get process for the reminder list servlet
     *
     * @param req the server request
     * @param resp the server response
     * @throws ServletException general servlet exception
     * @throws IOException general io exception
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String redirectCheck;

        userDao = new UserDao();
        user = new User();
        movieApiCalls = new MovieApiCalls();
        movies = new ArrayList<Movie>();
        userName = req.getRemoteUser();

        redirectCheck = getUser(session);

        if (redirectCheck.equals("")) {
            redirectCheck = addMovie(session);

            if (redirectCheck.equals("")) {
                req.setAttribute("movies", movies);
                String url = "/reminder-list.jsp";
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

                dispatcher.forward(req, resp);
            } else {
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirectCheck);

                dispatcher.forward(req, resp);
            }
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(redirectCheck);

            dispatcher.forward(req, resp);
        }
    }

    /**
     * Gets the user information from the database
     *
     * @param session the current server session
     * @return the error site if an error occurs
     */
    private String getUser(HttpSession session) {
        try {
            user = userDao.getUser(userName);
            return "";
        } catch (HibernateException he) {
            log.error("Hibernate Exception gathering user " + userName, he);
            session.setAttribute("error", "Error gathering user to update reminders");
            return "errorPage";
        }
    }

    /**
     * Calls the api for a movie and adds it to the array list
     *
     * @param session the current server session
     * @return the error site if an error occurs
     */
    private String addMovie(HttpSession session) {
        int movieId = 0;

        try {
            for (Reminders reminders : user.getReminders()) {
                movieId = reminders.getMovieId();
                movies.add(movieApiCalls.getMovieInfo(movieId));
            }

            return "";
        } catch (IOException io) {
            log.error("IOException when calling api for movie " + movieId, io);
            session.setAttribute("error", "Error finding movie using api");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception when calling api for movie " + movieId, e);
            session.setAttribute("error", "Error finding movie using api");
            return "errorPage";
        }
    }
}
