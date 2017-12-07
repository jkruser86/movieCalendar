package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.entity.*;
import edu.matc.movieCalendar.persistence.*;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The servlet that handles the create user submission
 *
 * @author Jamie Kruser
 */
@WebServlet (
        name = "submit",
        urlPatterns = {"/submit"}
)
public class CreateUserSubmitServlet extends HttpServlet {

    private final Logger log = Logger.getLogger(this.getClass());

    private String userName;
    private String password;
    private String email;

    private User user;
    private UserRoles userRoles;
    private UserDao userDao;
    private List<User> users;

    /**
     * The doPost for the create user submit servlet
     *
     * @param req the request for the servlet
     * @param resp the response for the servlet
     * @throws ServletException  handles the ServletException
     * @throws IOException handles the IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String userError = null;
        user = null;
        userRoles = new UserRoles();
        userDao = new UserDao();
        users = new ArrayList<User>();
        HttpSession session = req.getSession();
        String redirectCheck;

        userName = req.getParameter("userName");
        password = req.getParameter("password");
        email = req.getParameter("email");

        redirectCheck = getUsers(session);

        if (redirectCheck.equals("")) {
            userError = checkUserLoop(users);
            if (!userError.equals("")) {
                session.setAttribute("createUserError", userError);
                resp.sendRedirect("create-acct");
            } else {
                redirectCheck = createUser(session);

                if (redirectCheck.equals("")) {
                    req.login(userName, password);
                    resp.sendRedirect("account");
                } else {
                    resp.sendRedirect(redirectCheck);
                }
            }
        } else {
            resp.sendRedirect(redirectCheck);
        }
    }

    /**
     * Gets all users from the database
     *
     * @param session the current session for the server
     * @return the response page to forward if anything goes wrong
     */
    private String getUsers(HttpSession session) {
        try {
            users = userDao.getAllUsers();
            return "";
        } catch (HibernateException he) {
            log.error("Hibernate Exception gathering all users", he);
            session.setAttribute("error", "Error selecting all users on creating user");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception gathering all users", e);
            session.setAttribute("error", "Error selecting all users on creating user");
            return "errorPage";
        }
    }

    /**
     * Checks to see if user name or email already exist
     *
     * @param users the list of users
     * @return a string value if either username or email already exist; otherwise spaces
     */
    protected String checkUserLoop(List<User> users) {
        for (User user: users) {
            if (user.getUserName().equals(userName)) {
                return "username";
            } else if (user.getUserEmail().equals(email)) {
                return "email";
            }
        }

        return "";
    }

    /**
     * Creates the user with the passed in values to the servlet
     *
     * @param session the current server session
     * @return the error site if an error occurs
     */
    private String createUser(HttpSession session) {
        user = new User();
        user.setUserName(userName);
        user.setUserPass(password);
        user.setUserEmail(email);

        userRoles.setRoleName("user");
        userRoles.setUser(user);

        user.getUserRoles().add(userRoles);

        try {
            userDao.addUser(user);
            return "";
        } catch (HibernateException he) {
            log.error("Hibernate Exception creating user " + userName, he);
            session.setAttribute("error", "Error creating user");
            return "errorPage";
        } catch (Exception e) {
            log.error("Exception creating user " + userName, e);
            session.setAttribute("error", "Error creating user");
            return "errorPage";
        }

    }
}