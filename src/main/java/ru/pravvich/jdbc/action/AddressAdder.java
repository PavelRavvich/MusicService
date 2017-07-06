package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Adder of user address.
 */
public class AddressAdder extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public AddressAdder(final Connection connection,
                        final PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Addition address in table address.
     *
     * @param address for addition.
     * @return unique id of address. When fail of addition then return -1.
     */
    public int addAddress(final Address address) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("add_address"))) {


            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());

            final ResultSet set = statement.executeQuery();

            if (set.next()) return set.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1;
    }
}
