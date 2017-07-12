package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Address;
import ru.pravvich.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Getter for client by id from users table.
 */
public class ClientGetter extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public ClientGetter(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Get client by id.
     *
     * @param id of client.
     * @return Client with param id. If id not exist return empty Client obj.
     */
    public Client getClientById(final int id) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("get_client"))) {

            statement.setInt(1, id);


            try (final ResultSet set = statement.executeQuery()) {

                if (set.next()) {

                    final Client result = new Client();

                    result.setId(id);
                    result.setName(set.getString("name"));
                    result.setLogin(set.getString("login"));
                    result.setPassword(set.getString("password"));
                    result.setEmail(set.getString("email"));
                    result.setCreate(set.getTimestamp("create_date"));
                    result.setRoleId(set.getInt("role"));
                    result.setAddress(new Address(set.getInt("address")));

                    return result;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new Client();
    }
}
