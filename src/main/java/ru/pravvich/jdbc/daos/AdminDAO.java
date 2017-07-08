package ru.pravvich.jdbc.daos;

import ru.pravvich.jdbc.action.*;
import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Client;

/**
 * Admin dao with all admin function.
 */
public class AdminDAO extends ModelDAO {
    /**
     * Default constructor.
     *
     * @param connection to database.
     * @param properties requests loader from file Properties.
     */
    public AdminDAO(final java.sql.Connection connection,
                    final PropertiesLoader properties) {
        super(connection, properties);
    }

    /**
     * Add client to database.
     *
     * @param client for addition.
     * @return id of added client, if addition fail return flag -1.
     */
    public int addClient(final Client client) {
        return new ClientAdder(connection, properties).addClient(client);
    }

    /**
     * Update client row in users table.
     *
     * @param client which contain new data.
     * @return true if updating success, else false.
     */
    public boolean updateClient(final Client client) {
        return new ClientUpdater(connection, properties).updateClient(client);
    }

    /**
     * Delete client by id.
     *
     * @param id of client for delete.
     * @return true if delete success, else false.
     */
    public boolean deleteClientById(final int id) {
        return new ClientDeleter(connection, properties).deleteClientById(id);
    }

    /**
     * Check login exist in database.
     *
     * @param login for check.
     * @return true if exist, else false.
     */
    public boolean loginUnique(final String login) {
        return new LoginExistChecker(connection, properties)
                .loginIsExist(login);
    }

    /**
     * Check password exist in database.
     *
     * @param password for check.
     * @return true if exist, else false.
     */
    public boolean passwordUnique(final String password) {
        return new PasswordExistChecker(connection, properties)
                .passwordIsExist(password);
    }
}
