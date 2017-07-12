package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Checker for unique of password.
 */
public class PasswordExistChecker extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public PasswordExistChecker(final Connection connection,
                                final PropertiesLoader scripts) {
        super(connection, scripts);
    }


    /**
     * Check password exist in database.
     *
     * @param password for check.
     * @return true if exist, else false.
     */
    public boolean passwordIsExist(final String password) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("password_exist"))) {


            statement.setString(1, password);

            try (final ResultSet set = statement.executeQuery()) {
                if (set.next()) return set.getBoolean(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
