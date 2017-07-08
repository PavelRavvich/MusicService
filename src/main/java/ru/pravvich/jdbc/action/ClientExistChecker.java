package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Checker exist of client by login and password.
 */
public class ClientExistChecker extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public ClientExistChecker(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Check exist client by login & password.
     *
     * @param login    of client.
     * @param password of client.
     * @return true if client with pair login & password exist, else false.
     */
    public boolean clientIsExist(final String login, final String password) {

        if (login == null || password == null) return false;

        try (PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("exist_client_by_l_p"))) {

            statement.setString(1, login);
            statement.setString(2, password);

            final ResultSet set = statement.executeQuery();

            if (set.next()) return set.getBoolean("exists");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
