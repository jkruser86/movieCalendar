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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The servlet that handles the delete account for movieCalendar
 *
 * @author Jamie Kruser
 */
@WebServlet(
        name = "deleteAcct",
        urlPatterns = {"/deleteAcct"}
)
public class DeleteAccountServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    /**
     * The doGet for the delete account servlet
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao dao = new UserDao();
        String userName = req.getRemoteUser();
        HttpSession session = req.getSession();
        String url;

        try {
            dao.deleteUser(userName);
            url = "/delete-account.jsp";
            session.invalidate();
            req.logout();
        } catch (HibernateException he) {
            log.error("Hibernate Exception deleting user " + userName, he);
            session.setAttribute("error", "Error deleting user");
            url = "/errorPage";
        } catch (Exception e) {
            log.error("Exception deleting user " + userName, e);
            session.setAttribute("error", "Error deleting user");
            url = "/errorPage";
        }

        //session.invalidate();
        //req.logout();

        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
