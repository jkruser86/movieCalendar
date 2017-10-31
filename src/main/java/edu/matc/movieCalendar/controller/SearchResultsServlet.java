package edu.matc.movieCalendar.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.themoviedb.moviesearch.Results;
import org.themoviedb.moviesearch.ResultsItem;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Client client = ClientBuilder.newClient();
        WebTarget target =
                client.target("https://api.themoviedb.org/3/search/movie?api_key=25363f0be2ee0fc2fd2e9caa793b33f4&query=" +
                req.getParameter("term"));
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);

        ObjectMapper mapper = new ObjectMapper();
        Results results = mapper.readValue(response, Results.class);

        List<ResultsItem> allResults = new ArrayList<ResultsItem>();
        allResults = results.getResults();

        for (ResultsItem item : allResults) {
            System.out.println("All Results: " + item.getTitle());
        }
        //System.out.println("results: " + results.toString());
        //ResultsItem result = results.getResults().get(0);

        req.setAttribute("movies", allResults);
        String url = "/search-results.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(req, resp);
    }
}