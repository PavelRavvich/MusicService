package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Checker for unique of login.
 */
public class LoginExistChecker extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public LoginExistChecker(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Check login exist in database.
     *
     * @param login for check.
     * @return true if exist, else false.
     */
    public boolean loginIsExist(final String login) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("login_exist"))) {


            statement.setString(1, login);

            final ResultSet set = statement.executeQuery();

            if (set.next()) return set.getBoolean(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
