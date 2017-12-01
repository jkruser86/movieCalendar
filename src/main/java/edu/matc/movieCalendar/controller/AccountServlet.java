package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.persistence.UserDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The servlet that handles the account page for movieCalendar
 *
 * @author Jamie Kruser
 */
@WebServlet(
        name = "account",
        urlPatterns = {"/account"}
)
public class AccountServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * The doGet for the account servlet
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String url;
        try {
            UserDao userDao = new UserDao();
            req.setAttribute("user", userDao.getUser(req.getRemoteUser()));
            url = "/account.jsp";
        } catch (HibernateException he) {
            log.error("Error while selecting user" + req.getRemoteUser(), he);
            url = "/login-failure";
        }

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

        dispatcher.forward(req, resp);
    }
}
