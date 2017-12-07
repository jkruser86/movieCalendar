package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.entity.User;
import edu.matc.movieCalendar.persistence.UserDao;
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
     * The doGet for the account servlet. Gathers account data and sets it as an attribute to the request
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        String url;

        try {
            UserDao userDao = new UserDao();
            User user = userDao.getUser(req.getRemoteUser());
            req.setAttribute("user", user);
            url = "/account.jsp";
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

            dispatcher.forward(req, resp);
        } catch (HibernateException he) {
            log.error("Hibernate Exception while selecting user " + req.getRemoteUser(), he);

            req.logout();

            session.setAttribute("error", "Error selecting user on logging in");
            url = "errorPage";
            resp.sendRedirect(url);

        } catch (Exception e) {
            log.error("Exception while selecting user " + req.getRemoteUser(), e);

            req.logout();

            session.setAttribute("error", "Error selecting user on logging in");
            url = "errorPage";
            resp.sendRedirect(url);

        }


    }
}
