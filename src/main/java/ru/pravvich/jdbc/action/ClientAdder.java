package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Add Client to table users.
 */
public class ClientAdder extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public ClientAdder(final Connection connection,
                       final PropertiesLoader scripts) {

        super(connection, scripts);
    }

    /**
     * Addition client to database.
     *
     * @param client    for addition to users table.
     * @return id of added client, if addition fail return flag -1.
     */
    public int addClient(final Client client) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("add_client"))) {


            statement.setString(2, client.getName());
            statement.setString(3, client.getLogin());
            statement.setString(4, client.getPassword());
            statement.setString(5, client.getEmail());
            statement.setInt(7, client.getRoleId());
            statement.setInt(8, client.getAddress().getId());


            final ResultSet set = statement.executeQuery();

            if (set.next()) return set.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
