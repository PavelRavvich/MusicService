package ru.pravvich.servlet;

import ru.pravvich.jdbc.GenericDAO;
import ru.pravvich.jdbc.daos.AdminDAO;
import ru.pravvich.model.Address;
import ru.pravvich.model.Client;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * RegistrationServlet.
 */
public class RegistrationServlet extends HttpServlet {

    private GenericDAO dao;

    @Override
    public void init() throws ServletException {
        super.init();
        dao = (GenericDAO) getServletContext().getAttribute("db");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final Client client = extractClient(req);

        if (userInputValid(client) && logPassIsUnique(client)) {

            final int addressId = addAddress(client.getAddress());

            client.getAddress().setId(addressId);

            final int id = addClientToDB(client);

            client.setId(id);

            addClientSession(client, req);

            req.getRequestDispatcher("/WEB-INF/views/UserMenu.jsp")
                    .forward(req, resp);
        } else {

            req.setAttribute("warning", "Enter unique login and password");

            req.getRequestDispatcher("/Registration.jsp")
                    .forward(req, resp);

        }
    }

    private int addAddress(final Address address) {
        return dao.getAdminDAO().addAddress(address);
    }

    private void addClientSession(Client client, HttpServletRequest req) {
        final HttpSession session = req.getSession();

        session.setAttribute("login", client.getLogin());
        session.setAttribute("password", client.getPassword());
        session.setAttribute("id", client.getId());
        session.setAttribute("access", 1);


    }

    private int addClientToDB(Client client) {
        return dao.getAdminDAO().addClient(client);
    }

    private boolean userInputValid(final Client client) {

        return client.getName() != null &&
                client.getLogin() != null &&
                client.getPassword() != null &&
                client.getAddress().getCountry() != null &&
                client.getAddress().getCity() != null &&
                client.getEmail() != null;
    }


    private boolean logPassIsUnique(final Client client) {
        final AdminDAO adminDAO = dao.getAdminDAO();
        final boolean logU = adminDAO.loginUnique(client.getLogin());
        final boolean pasU = adminDAO.passwordUnique(client.getPassword());
        return logU && pasU;
    }

    private Client extractClient(final HttpServletRequest req) {
        final String name = req.getParameter("username");
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String country = req.getParameter("country");
        final String city = req.getParameter("city");
        final String email = req.getParameter("email");

        return new Client(
                name,
                login,
                password,
                new Address(country, city),
                1,
                email
        );
    }
}
