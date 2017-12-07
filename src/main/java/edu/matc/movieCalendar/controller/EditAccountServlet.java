package edu.matc.movieCalendar.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet that handles the edit account page for movieCalendar
 *
 * @author Jamie Kruser
 */
@WebServlet(
        name = "editAccount",
        urlPatterns = {"/editAccount"}
)
public class EditAccountServlet extends HttpServlet {

    /**
     * The doPost for the edit account servlet. Forwards to edit-account.jsp
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = "/edit-account.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(req, resp);
    }
}
