package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.util.Movie;
import edu.matc.movieCalendar.util.MovieApiCalls;
import org.apache.log4j.Logger;
import org.themoviedb.moviesearch.MovieResultsItem;

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
 * This servlet handles the search results page for movieCalendar
 *
 * @author Jamie Kruser
 */
@WebServlet(
        name = "searchResults",
        urlPatterns = { "/search-results" }
)
public class SearchResultsServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    private String searchTerm;
    private MovieApiCalls movieApiCalls;
    private List<MovieResultsItem> allResults;
    private List<Movie> allMovies;

    /**
     * The doGet for the search results servlet
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        searchTerm = req.getParameter("term").replaceAll(" ", "+");
        movieApiCalls = new MovieApiCalls();
        allResults = new ArrayList<MovieResultsItem>();
        allMovies = new ArrayList<Movie>();

        HttpSession session = req.getSession();
        String redirectCheck;

        redirectCheck = allMovieResults(session);

        if (redirectCheck.equals("")) {
            redirectCheck = getMovieInfo(session);

            if (redirectCheck.equals("")) {
                req.setAttribute("movies", allMovies);
                String url = "/search-results.jsp";
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
     * Gets all the movies for the given search term from the api
     *
     * @param session the current server session
     * @return the error site if an error occurs
     */
    private String allMovieResults(HttpSession session) {
        try {
            allResults = movieApiCalls.getAllResults(searchTerm);
            return "";
        } catch (IOException io) {
            log.error("IOException getting all search results from api for term " + searchTerm, io);
            session.setAttribute("error", "Error gathering results from movie api");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception getting all search results from api for term " + searchTerm, e);
            session.setAttribute("error", "Error gathering results from movie api");
            return "errorPage";
        }
    }

    /**
     * Gets the movie info from the api for each movie
     *
     * @param session the current server session
     * @return the error site if an error occurs
     */
    private String getMovieInfo(HttpSession session) {

        int movieId = 0;

        try {
            for (MovieResultsItem item : allResults) {

                movieId = item.getId();
                Movie movie = movieApiCalls.getMovieInfo(movieId);
                allMovies.add(movie);
            }
            return "";
        } catch (IOException io ) {
            log.error("IOException getting movie data from api for movie " + movieId, io);
            session.setAttribute("error", "Error gathering movie info from api");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception getting movie data from api for movie " + movieId, e);
            session.setAttribute("error", "Error gathering movie info from api");
            return "errorPage";
        }
    }
}