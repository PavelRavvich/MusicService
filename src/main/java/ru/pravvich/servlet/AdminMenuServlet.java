package ru.pravvich.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Admin menu controller.
 */
public class AdminMenuServlet extends HttpServlet {
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final String act = req.getParameter("act");

        if (act.equals("some_cations")) {
            // TODO: 08.07.17
        }

    }
}
