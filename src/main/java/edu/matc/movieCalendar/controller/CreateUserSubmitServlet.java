package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.entity.*;
import edu.matc.movieCalendar.persistence.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet (
        name = "submit",
        urlPatterns = {"/submit"}
)
public class CreateUserSubmitServlet extends HttpServlet {

    private User user;
    private UserRoles userRoles;
    private UserDao userDao;
    private UserRolesDao userRolesDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        user = null;
        userRoles = new UserRoles();
        userDao = new UserDao();
        userRolesDao = new UserRolesDao();

        String userName = req.getParameter("userName");
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        user = userDao.getUser(userName);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("createUserError", "Username already exists");
            resp.sendRedirect("create-acct");
            //RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/create-acct");
        } else {
            user = new User();
            user.setUserName(userName);
            user.setUserPass(password);
            user.setUserEmail(email);

            userDao.addUser(user);

            userRoles.setUserName(userName);
            userRoles.setRoleName("user");

            userRolesDao.addUserRoles(userRoles);

            req.login(userName, password);

            HttpSession session = req.getSession();
            session.setAttribute("userSignup","Thanks for signing up. Please sign in to your account");
            resp.sendRedirect("account");
        }
    }
}