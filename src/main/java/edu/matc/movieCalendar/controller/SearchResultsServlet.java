package edu.matc.movieCalendar.controller;

import com.sun.deploy.net.HttpResponse;

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
import java.io.IOException;

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
                req.getParameter("searchTerm"));

        String url = "/search-results.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(req, resp);
    }
}