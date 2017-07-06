package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Address;
import ru.pravvich.model.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by pavel on 03.07.17.
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

    public boolean addClient(final Client client) {

        try (PreparedStatement statement = connection

                .prepareStatement(scripts.get("add_client"))) {




        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Add address of client.
     *
     * @param client which contain address.
     * @return id of address in "address" table.
     */
    private int addAddress(final Client client) throws SQLException {

        int result = 0;

        final Address address = client.getAddress();

        try (PreparedStatement statement = connection

                .prepareStatement(scripts.get("add_address"))) {


            statement.setString(1, address.getCountry());

            statement.setString(2, address.getCity());

            final ResultSet set = statement.executeQuery();

            if (set.next()) {
                result = set.getInt(1);
            }

            return result;
        }
    }
}
