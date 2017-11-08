package edu.matc.movieCalendar.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "movieSave",
        urlPatterns = { "/movieSave" }
)

public class MovieSaveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String movieId = req.getParameter("movie_id");
        String theaterNumber = req.getParameter("theater_number");
        String theaterTimeframe = req.getParameter("theater_timeframe");
        String digitalNumber = req.getParameter("digital_number");
        String digitalTimeframe = req.getParameter("digital_timeframe");
        String physicalNumber = req.getParameter("physical_number");
        String physicalTimeframe = req.getParameter("physical_timeframe");
        System.out.println(movieId);
    }
}
