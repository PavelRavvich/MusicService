package ru.pravvich.jdbc;

import ru.pravvich.jdbc.action.ClientAdder;
import ru.pravvich.jdbc.action.ClientDeleter;
import ru.pravvich.jdbc.action.ClientUpdater;
import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Client;

/**
 * Created by pavel on 03.07.17.
 */
public class AdminDAO extends ModeratorDAO {
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

    public boolean addClient(final Client client) {
        return new ClientAdder(connection, properties).addClient(client);
    }

    public boolean updateClient(final Client client) {
        return new ClientUpdater(connection, properties).updateClient(client);
    }

    public boolean deleteClientById(final int id) {
        return new ClientDeleter(connection, properties).deleteClientById(id);
    }
}
