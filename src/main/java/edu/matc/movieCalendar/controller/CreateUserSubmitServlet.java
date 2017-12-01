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
        List<User> users = new ArrayList<User>();
        HttpSession session = req.getSession();

        userName = req.getParameter("userName");
        password = req.getParameter("password");
        email = req.getParameter("email");

        try {
            users = userDao.getAllUsers();
            userError = checkUserLoop(users);

        } catch (HibernateException he) {
            log.error("Error gathering all users", he);
            session.setAttribute("error", "Error selecting all users on creating user");
            resp.sendRedirect("errorPage");
        }

        if (!userError.equals("")) {
            session.setAttribute("createUserError", userError);
            resp.sendRedirect("create-acct");
        } else {
            try {
                createUser();
                req.login(userName, password);
                resp.sendRedirect("account");
            } catch (HibernateException he) {
                log.error("Error creating user " + userName, he);
                session.setAttribute("error", "Error creating user");
                resp.sendRedirect("errorPage");
            }
        }
    }

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

    protected void createUser() throws HibernateException {
        user = new User();
        user.setUserName(userName);
        user.setUserPass(password);
        user.setUserEmail(email);

        userRoles.setRoleName("user");
        userRoles.setUser(user);

        user.getUserRoles().add(userRoles);

        userDao.addUser(user);
    }
}