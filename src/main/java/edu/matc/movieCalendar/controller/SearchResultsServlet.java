package edu.matc.movieCalendar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.matc.movieCalendar.util.Movie;
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

    private final String apiKey = "25363f0be2ee0fc2fd2e9caa793b33f4";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String searchTerm = req.getParameter("term").replaceAll(" ", "+");
        List<MovieResultsItem> allResults = getAllResults(searchTerm);

        List<Movie> allMovies = new ArrayList<Movie>();
        for (MovieResultsItem item : allResults) {

            Movie movie = new Movie(item, apiKey);
            allMovies.add(movie);
            //System.out.println("All Results: " + item.getTitle());
        }
        //System.out.println("results: " + results.toString());
        //MovieResultsItem result = results.getResults().get(0);

        req.setAttribute("movies", allMovies);
        String url = "/search-results.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(req, resp);
    }

    private List<MovieResultsItem> getAllResults(String term) throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/search/movie?api_key=" + apiKey + "&query=" + term);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        MovieResults results = mapper.readValue(response, MovieResults.class);

        return results.getResults();
    }
}