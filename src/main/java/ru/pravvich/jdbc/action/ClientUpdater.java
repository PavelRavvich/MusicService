package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Client;

import java.sql.Connection;

/**
 * Created by pavel on 03.07.17.
 */
public class ClientUpdater extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public ClientUpdater(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    public boolean updateClient(final Client client) {
        return false;
    }
}
