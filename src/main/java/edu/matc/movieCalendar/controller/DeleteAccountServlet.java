package edu.matc.movieCalendar.controller;

import edu.matc.movieCalendar.persistence.UserDao;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(
        name = "deleteAcct",
        urlPatterns = {"/deleteAcct"}
)
public class DeleteAccountServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDao dao = new UserDao();
        String userName = req.getRemoteUser();

        HttpSession session = req.getSession();
        session.invalidate();

        req.logout();

        dao.deleteUser(userName);

        String url = "/delete-account.jsp";
        RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(url);
        dispatcher.forward(req, resp);
    }
}
