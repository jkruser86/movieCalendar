package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.entity.User;
import edu.matc.movieCalendar.persistence.UserDao;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//TODO: Add check for existing email
//TODO: Send message back to site showing successful save
/**
 * The servlet that handles the saving for the account after editing for movieCalendar
 *
 * @author Jamie Kruser
 */
@WebServlet (
        name = "saveEdit",
        urlPatterns = {"/saveEdit"}
)
public class EditAccountSaveServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    private User user;
    private UserDao userDao;

    /**
     * The doPost for the account edit save servlet
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = new User();
        userDao = new UserDao();

        String userName = req.getRemoteUser();
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        HttpSession session = req.getSession();

        try {
            user = userDao.getUser(userName);
        } catch (HibernateException he) {
            log.error("Hibernate Exception finding user " + userName + " to update", he);
            session.setAttribute("error", "Error finding user to update");
            resp.sendRedirect("errorPage");
        } catch (Exception e) {
            log.error("Exception finding user " + userName + " to update", e);
            session.setAttribute("error", "Error finding user to update");
            resp.sendRedirect("errorPage");
        }

        user.setUserPass(password);
        user.setUserEmail(email);

        try {
            userDao.updateUser(user);
        } catch (HibernateException he) {
            log.error("Hibernate Exception updating user " + userName, he);
            session.setAttribute("error", "Error updating user info");
            resp.sendRedirect("errorPage");
        } catch (Exception e) {
            log.error("Exception updating user " + userName, e);
            session.setAttribute("error", "Error updating user info");
            resp.sendRedirect("errorPage");
        }

        resp.sendRedirect("account");
    }
}
