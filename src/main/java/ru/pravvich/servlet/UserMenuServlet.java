package ru.pravvich.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User Menu. Send client to servlet by select.
 */
public class UserMenuServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final String act = req.getParameter("act");

        if (act.equals("edit")) {

            req.getRequestDispatcher("/user_menu/edit_music")
                    .forward(req, resp);

        } else if (act.equals("add")) {

            req.getRequestDispatcher("/WEB-INF/views/AddMusic.jsp")
                    .forward(req, resp);

        } else if (act.equals("delete")){

            req.getRequestDispatcher("/WEB-INF/views/DeleteMusic.jsp")
                    .forward(req, resp);
        }
    }
}
