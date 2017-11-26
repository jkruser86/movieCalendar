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

        user = userDao.getUser(userName);
        user.setUserPass(password);
        user.setUserEmail(email);

        userDao.updateUser(user);

        HttpSession session = req.getSession();
        resp.sendRedirect("account");
    }
}
