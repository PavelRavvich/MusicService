package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Checker of user address.
 */
public class AddressExistChecker extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public AddressExistChecker(final Connection connection,
                               final PropertiesLoader scripts) {
        super(connection, scripts);
    }

    /**
     * Check exist of pair country/city in table address.
     *
     * @param address for check.
     * @return true if exist, else false.
     */
    public boolean addressIsExist(final Address address) {

        try (final PreparedStatement statement =

                     connection.prepareStatement(

                             scripts.get("address_exist"))) {


            statement.setString(1, address.getCountry());
            statement.setString(2, address.getCity());

            final ResultSet set = statement.executeQuery();

            if (set.next()) return set.getBoolean(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
