package edu.matc.movieCalendar.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet that handles the account creation page for movieCalendar
 *
 * @author Jamie Kruser
 */
@WebServlet (
        name = "create-acct",
        urlPatterns = {"/create-acct"}
)
public class CreateUserServlet extends HttpServlet {

    /**
     * The doGet for the account creation servlet. Forwards to the create-acct.jsp
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/create-acct.jsp";

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(req, resp);

    }
}
