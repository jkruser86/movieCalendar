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
import java.util.List;

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
        String redirectCheck;

        redirectCheck = checkEmailExists(session, email, userName);

        if (redirectCheck == "") {
            redirectCheck = getUser(session, userName);
            if (redirectCheck == "") {
                user.setUserEmail(email);
                user.setUserPass(password);

                redirectCheck = updateUser(session);

                if (redirectCheck == "") {
                    resp.sendRedirect("account");
                } else {
                    String url = redirectCheck;
                    RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

                    dispatcher.forward(req, resp);
                }

            } else {
                String url = redirectCheck;
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

                dispatcher.forward(req, resp);
            }
        } else {
            String url = redirectCheck;
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);

            dispatcher.forward(req, resp);
        }
    }

    /**
     * Checks all users to see if email address exists
     *
     * @param session the current HttpSession
     * @param email the email address the user wants to update
     * @param userName the userName of the borrower to update
     * @return the response page to forward if anything goes wrong
     */
    protected String checkEmailExists(HttpSession session, String email, String userName) {
        try {
            List<User> users = userDao.getAllUsers();
            for (User emailUser : users) {
                if (emailUser.getUserEmail().equals(email) && !emailUser.getUserName().equals(userName)) {
                    session.setAttribute("createUserError", "email");
                    return "/editAccount";
                }
            }
        } catch (HibernateException he) {
            log.error("Hibernate Exception getting all users for email check", he);
            session.setAttribute("error", "Error checking for email existing");
            return "/errorPage";
        } catch (Exception e) {
            log.error("Exception getting all users for email check", e);
            session.setAttribute("error", "Error checking for email existing");
            return "/errorPage";
        }

        return "";
    }

    /**
     * Gets the User from the database
     *
     * @param session the current HttpSession
     * @param userName the user name for the account to update
     * @return the response page to forward if anything goes wrong
     */
    protected String getUser(HttpSession session, String userName) {
        try {
            user = userDao.getUser(userName);
            return "";
        } catch (HibernateException he) {
            log.error("Hibernate Exception finding user " + userName + " to update", he);
            session.setAttribute("error", "Error finding user to update");
            return "/errorPage";
        } catch (Exception e) {
            log.error("Exception finding user " + userName + " to update", e);
            session.setAttribute("error", "Error finding user to update");
            return "/errorPage";
        }
    }

    /**
     * Updates the user with the passed in values
     *
     * @param session the current HttpSession
     * @return the response page to forward after the update occurs or if any errors occur
     */
    protected String updateUser(HttpSession session) {
        try {
            userDao.updateUser(user);
            session.setAttribute("updatedAcct", true);
            return "";
        } catch (HibernateException he) {
            log.error("Hibernate Exception updating user " + user.getUserName(), he);
            session.setAttribute("error", "Error updating user info");
            return "/errorPage";
        } catch (Exception e) {
            log.error("Exception updating user " + user.getUserName(), e);
            session.setAttribute("error", "Error updating user info");
            return "/errorPage";
        }
    }
}
