package ru.pravvich.jdbc.action;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddressExistCheckerTest {

    @Test
    public void whenAddressIsExistThenAddressIsExistReturnTrue()
            throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("address_exist")).thenReturn("mock_script");

        final ResultSet set = mock(ResultSet.class);
        when(set.getBoolean(1)).thenReturn(true);
        when(set.next()).thenReturn(true);

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final AddressExistChecker checker =
                new AddressExistChecker(connection, properties);

        final Address address = new Address("test", "test");
        final boolean result = checker.addressIsExist(address);

        Assert.assertTrue(result);

        verify(properties).get("address_exist");
        verify(connection).prepareStatement("mock_script");
        verify(statement).setString(1, "test");
        verify(statement).setString(2, "test");
        verify(statement).executeQuery();
        verify(set).next();
        verify(set).getBoolean(1);
    }

    @Test
    public void whenAddressNotExistInAddressTableThenAddressIsExistReturnFalse()
            throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("address_exist")).thenReturn("mock_script");

        final ResultSet set = mock(ResultSet.class);
        when(set.getBoolean(1)).thenReturn(false);
        when(set.next()).thenReturn(true);

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final AddressExistChecker checker =
                new AddressExistChecker(connection, properties);

        final Address address = new Address("test", "test");
        final boolean result = checker.addressIsExist(address);

        Assert.assertFalse(result);

        verify(properties).get("address_exist");
        verify(connection).prepareStatement("mock_script");
        verify(statement).setString(1, "test");
        verify(statement).setString(2, "test");
        verify(statement).executeQuery();
        verify(set).next();
        verify(set).getBoolean(1);
    }

    @Test
    public void whenResultSetNextReturnFalseThenReturnFalse()
            throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("address_exist")).thenReturn("mock_script");

        final ResultSet set = mock(ResultSet.class);
        when(set.getBoolean(1)).thenReturn(true);
        when(set.next()).thenReturn(false);

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final AddressExistChecker checker =
                new AddressExistChecker(connection, properties);

        final Address address = new Address("test", "test");
        final boolean result = checker.addressIsExist(address);

        Assert.assertFalse(result);

        verify(properties).get("address_exist");
        verify(connection).prepareStatement("mock_script");
        verify(statement).setString(1, "test");
        verify(statement).setString(2, "test");
        verify(statement).executeQuery();
        verify(set).next();
    }

    @Test
    public void whenTrowSQLException() throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("address_exist")).thenReturn("mock_script");

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script"))
                .thenThrow(mock(SQLException.class));

        final AddressExistChecker checker =
                new AddressExistChecker(connection, properties);

        final Address address = new Address("test", "test");
        final boolean result = checker.addressIsExist(address);

        Assert.assertFalse(result);

        verify(properties).get("address_exist");

        verify(connection).prepareStatement("mock_script");
    }
}