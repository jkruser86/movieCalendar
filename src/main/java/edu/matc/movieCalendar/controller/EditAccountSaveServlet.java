package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.entity.User;
import edu.matc.movieCalendar.persistence.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet (
        name = "saveEdit",
        urlPatterns = {"/saveEdit"}
)
public class EditAccountSaveServlet extends HttpServlet {

    private User user;
    private UserDao userDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        user = new User();
        userDao = new UserDao();

        String userName = req.getRemoteUser();
        String password = req.getParameter("password");
        String email = req.getParameter("email");

        user.setUserName(userName);
        user.setUserPass(password);
        user.setUserEmail(email);

        userDao.updateUser(user);

        HttpSession session = req.getSession();
        resp.sendRedirect("account");
    }
}
