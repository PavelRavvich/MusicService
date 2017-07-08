package ru.pravvich.jdbc.action;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AddressAdderTest {
    @Test
    public void whenAddressAddSuccessThenReturnID() throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("add_address")).thenReturn("mock_script");

        final ResultSet set = mock(ResultSet.class);
        when(set.getInt(1)).thenReturn(1);
        when(set.next()).thenReturn(true);

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final AddressAdder adder =
                new AddressAdder(connection, properties);

        final Address address = new Address("test", "test");
        final int result = adder.addAddress(address);

        Assert.assertThat(result, is(1));

        verify(properties).get("add_address");
        verify(connection).prepareStatement("mock_script");
        verify(statement).setString(1, "test");
        verify(statement).setString(2, "test");
        verify(statement).executeQuery();
        verify(set).next();
        verify(set).getInt(1);
    }

    @Test
    public void whenPrepareStatementTrowSQLException() throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("add_address")).thenReturn("mock_script");

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script"))
                .thenThrow(mock(SQLException.class));


        final AddressAdder adder =
                new AddressAdder(connection, properties);

        final Address address = new Address("test", "test");
        final int result = adder.addAddress(address);

        Assert.assertThat(result, is(-1));

        verify(properties).get("add_address");

        verify(connection).prepareStatement("mock_script");
    }

    @Test
    public void whenGetIntTrowSQLException() throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("add_address")).thenReturn("mock_script");

        final ResultSet set = mock(ResultSet.class);
        when(set.getInt(1)).thenThrow(mock(SQLException.class));
        when(set.next()).thenReturn(true);

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final AddressAdder adder =
                new AddressAdder(connection, properties);

        final Address address = new Address("test", "test");
        final int result = adder.addAddress(address);

        Assert.assertThat(result, is(-1));

        verify(properties).get("add_address");
        verify(connection).prepareStatement("mock_script");
        verify(statement).setString(1, "test");
        verify(statement).setString(2, "test");
        verify(statement).executeQuery();
        verify(set).next();
        verify(set).getInt(1);
    }

    @Test
    public void whenNextReturnFalseThenReturnFailFlag() throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("add_address")).thenReturn("mock_script");

        final ResultSet set = mock(ResultSet.class);
        when(set.next()).thenReturn(false);

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final AddressAdder adder =
                new AddressAdder(connection, properties);

        final Address address = new Address("test", "test");
        final int result = adder.addAddress(address);

        Assert.assertThat(result, is(-1));

        verify(properties).get("add_address");
        verify(connection).prepareStatement("mock_script");
        verify(statement).setString(1, "test");
        verify(statement).setString(2, "test");
        verify(statement).executeQuery();
        verify(set).next();
    }
}