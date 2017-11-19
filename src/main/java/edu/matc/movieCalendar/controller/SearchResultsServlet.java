package edu.matc.movieCalendar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.movieCalendar.util.Movie;
import edu.matc.movieCalendar.util.MovieApiCalls;
import org.apache.log4j.Logger;
import org.themoviedb.moviesearch.MovieResultsItem;
import org.themoviedb.moviesearch.ReleaseDatesItem;
import org.themoviedb.moviesearch.MovieResults;
import org.themoviedb.moviesearch.ReleaseDatesResults;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(
        name = "searchResults",
        urlPatterns = { "/search-results" }
)

public class SearchResultsServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchTerm = req.getParameter("term").replaceAll(" ", "+");
        MovieApiCalls movieApiCalls = new MovieApiCalls();
        List<MovieResultsItem> allResults = new ArrayList<MovieResultsItem>();

        try {
            allResults = movieApiCalls.getAllResults(searchTerm);
        } catch (IOException io) {
            log.error("Error getting all search results", io);
        }

        List<Movie> allMovies = new ArrayList<Movie>();
        for (MovieResultsItem item : allResults) {

            Movie movie = movieApiCalls.getMovieInfo(item.getId());
            allMovies.add(movie);
        }

        req.setAttribute("movies", allMovies);
        String url = "/search-results.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(req, resp);
    }
}