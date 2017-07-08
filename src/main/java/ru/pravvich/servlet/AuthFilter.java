package ru.pravvich.servlet;

import ru.pravvich.jdbc.GenericDAO;
import ru.pravvich.model.Client;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

/**
 * Acidification filter.
 */
public class AuthFilter implements Filter {

    private final static String password = "password";
    private final static String access = "access";
    private final static String admin = "admin";
    private final static String login = "login";
    private final static String user = "user";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)

            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;

        final GenericDAO dao = getGenericDAO(req);


        final HttpSession session = req.getSession(false);

        if (nonNull(session) && userExistIn(session)) {

            final int success = getSessionAccess(session);

            moveToMenu(req, res, success);

            filterChain.doFilter(request, response);

        } else if (userExistInDB(req, dao)) {

            final int access = getAccess(req, dao);

            final int id = getId(req, dao);

            putSessionAttributes(req, access, id);

            moveToMenu(req, res, access);

        } else {

            moveToLoginPage(req, res);
        }
    }

    /**
     * Move user to menu.
     * If access 'admin' move to admin menu.
     * If access 'user' move to user menu.
     * Else move to ErrorPage.
     *
     * @param access level.
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final int access)

            throws ServletException, IOException {

        if (access == 3) {

            req.getRequestDispatcher("/WEB-INF/views/AdminMenu.jsp")
                    .forward(req, res);

        } else if (access == 2) {

            req.getRequestDispatcher("/WEB-INF/views/ModeratorMenu.jsp")
                    .forward(req, res);

        } else if (access == 1) {

            req.getRequestDispatcher("/WEB-INF/views/UserMenu.jsp")
                    .forward(req, res);

        } else {

            req.getRequestDispatcher("/Error.jsp").forward(req, res);

        }
    }

    /**
     * Put attributes in session.
     * Put user's login and access level and id into session.
     *
     * @param access for put.
     * @param id     for put.
     */
    private void putSessionAttributes(final HttpServletRequest req,
                                      final int access,
                                      final int id)

            throws ServletException, IOException {

        final HttpSession session = req.getSession();

        synchronized (session) {
            session.setAttribute(login, req.getParameter(login));
            session.setAttribute("access", access);
            session.setAttribute("id", id);
        }
    }

    /**
     * Get access from current session.
     */
    private int getSessionAccess(final HttpSession session) {
        synchronized (session) {
            return (int) session.getAttribute(access);
        }
    }

    /**
     * Recognition user.
     *
     * @param session currant session.
     * @return true if session contain name and user recognition, else false.
     */
    private boolean userExistIn(final HttpSession session)
            throws ServletException, IOException {

        synchronized (session) {
            return (nonNull(session.getAttribute(login)));
        }
    }

    /**
     * Move to login page if pare login & password not exist in database.
     */
    private void moveToLoginPage(final HttpServletRequest req,
                                 final HttpServletResponse res)

            throws ServletException, IOException {


        if (req.getParameter(login) != null) {
            req.setAttribute("warning", "Login password not found.");
        }

        req.getRequestDispatcher("/Login.jsp").forward(req, res);
    }


    /**
     * Check in database exist user or not.
     *
     * @return true if user exist in DB, else false.
     */
    private boolean userExistInDB(final HttpServletRequest request,
                                  final GenericDAO dao) {

        final String log = request.getParameter(login);
        final String pas = request.getParameter(password);

        return dao.getModeratorDAO().clientIsExist(log, pas);
    }

    /**
     * Get access level from database by pair login and password.
     *
     * @return access level.
     */
    private int getAccess(final HttpServletRequest request,
                             final GenericDAO dao) {

        final String log = request.getParameter(login);
        final String pas = request.getParameter(password);

        return dao.getModeratorDAO().getAccessLogPass(log, pas);

    }

    /**
     * For work with database.
     *
     * @return database GenericDAO.
     */
    private GenericDAO getGenericDAO(final ServletRequest req) {

        return (GenericDAO) req.getServletContext()
                .getAttribute("db");

    }

    /**
     * Get id for user with pair login password from database.
     */
    private int getId(final HttpServletRequest req,
                      final GenericDAO dao) {


        final String login = req.getParameter(AuthFilter.login);
        final String password = req.getParameter(AuthFilter.password);

        final Client client = dao.getModeratorDAO()
                .getClientLogPass(login, password);

        return client.getId();
    }

    @Override
    public void destroy() {
    }
}
