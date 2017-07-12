package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Getter for role id of Client with concrete pair login & password.
 */
public class RoleIdGetter extends Action{
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public RoleIdGetter(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Get role id by pair login & password.
     *
     * @param login of Client.
     * @param password of Client.
     * @return id of role. If Client not found return flag 0.
     * If login or password == null return flag 0;
     */
    public int getRoleIdByLogPas(final String login, final String password) {

        if (login == null || password == null) return 0;

        try (PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("get_role"))){


            statement.setString(1, login);
            statement.setString(2, password);

            try (final ResultSet set = statement.executeQuery()) {
                if (set.next()) return set.getInt("role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
