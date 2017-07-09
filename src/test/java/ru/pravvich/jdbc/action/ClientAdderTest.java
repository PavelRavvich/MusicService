package ru.pravvich.jdbc.action;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Address;
import ru.pravvich.model.Client;

import java.sql.*;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientAdderTest {
    @Test
    public void whenClientAdditionSuccessThenReturnClientID()
            throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("add_client")).thenReturn("mock_script");

        final ResultSet set = mock(ResultSet.class);
        when(set.getInt(1)).thenReturn(1);
        when(set.next()).thenReturn(true);

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final ClientAdder adder =
                new ClientAdder(connection, properties);

        final Address address = new Address("test", "test", 1);
        final Client client = new Client("test", "test", "test", address,
                new ArrayList<>(), new Timestamp(System.currentTimeMillis()),
                "test", "test");
        final int result = adder.addClient(client);

        Assert.assertThat(result, is(1));

        verify(properties).get("add_client");
        verify(connection).prepareStatement("mock_script");

        verify(statement).setString(1, client.getName());
        verify(statement).setString(2, client.getLogin());
        verify(statement).setString(3, client.getPassword());
        verify(statement).setString(4, client.getEmail());
        verify(statement).setInt(5, client.getRoleId());
        verify(statement).setInt(6, client.getAddress().getId());

        verify(statement).executeQuery();
        verify(set).next();
        verify(set).getInt(1);
    }

    @Test
    public void whenPrepareStatementTrowSQLException() throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("add_client")).thenReturn("mock_script");

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script"))
                .thenThrow(mock(SQLException.class));

        final ClientAdder adder =
                new ClientAdder(connection, properties);

        final Address address = new Address("test", "test", 1);
        final Client client = new Client("test", "test", "test", address,
                new ArrayList<>(), new Timestamp(System.currentTimeMillis()),
                "test", "test");
        final int result = adder.addClient(client);

        Assert.assertThat(result, is(-1));

        verify(properties).get("add_client");

        verify(connection).prepareStatement("mock_script");
    }
}