package ru.pravvich.jdbc.action;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Address;
import ru.pravvich.model.Client;

import java.sql.*;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientGetterTest {

    @Test
    public void whenClientAdditionSuccessThenReturnClientID()
            throws SQLException {

        final Address address = new Address("test", "test", 1);

        Client client = new Client(1, "test", "test", "test", address,
                new Timestamp(System.currentTimeMillis()), 1, "test");


        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("get_client")).thenReturn("mock_script");

        final ResultSet set = mock(ResultSet.class);
        when(set.next()).thenReturn(true);

        when(set.getString("name")).thenReturn(client.getName());
        when(set.getString("login")).thenReturn(client.getLogin());
        when(set.getString("password")).thenReturn(client.getPassword());
        when(set.getString("email")).thenReturn(client.getEmail());
        when(set.getTimestamp("create_date")).thenReturn(client.getCreate());
        when(set.getInt("role")).thenReturn(client.getRoleId());
        when(set.getInt("address")).thenReturn(client.getAddress().getId());

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final ClientGetter getter =
                new ClientGetter(connection, properties);


        final Client result = getter.getClientById(1);


        Assert.assertThat(result, is(client));

        verify(properties).get("get_client");
        verify(connection).prepareStatement("mock_script");
        verify(statement).executeQuery();
        verify(set).next();
    }

    @Test
    public void whenConnectionThrowSQLExceptionReturnEmptyClientObj()
            throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("get_client")).thenReturn("mock_script");

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script"))
                .thenThrow(mock(SQLException.class));


        final ClientGetter getter =
                new ClientGetter(connection, properties);


        final Client result = getter.getClientById(1);

        Assert.assertNull(result.getLogin());
        Assert.assertNull(result.getPassword());
        Assert.assertNull(result.getEmail());
        Assert.assertThat(result.getId(), is(0));
    }
}