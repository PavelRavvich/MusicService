package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Client;

import java.sql.Connection;
import java.util.List;

/**
 * Created by pavel on 03.07.17.
 */
public class ClientGetterRole extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public ClientGetterRole(final Connection connection,
                            final PropertiesLoader scripts) {
        super(connection, scripts);
    }

    public List<Client> getClientsByRole(final String role) {
        return null;
    }
}
