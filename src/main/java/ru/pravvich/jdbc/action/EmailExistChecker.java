package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;

/**
 * Created by pavel on 06.07.17.
 */
public class EmailExistChecker extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public EmailExistChecker(final Connection connection,
                             final PropertiesLoader scripts) {
        super(connection, scripts);
    }

    // TODO: 06.07.17 add e,ail validation
}
