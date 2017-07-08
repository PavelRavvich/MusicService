package ru.pravvich.jdbc.action;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientExistCheckerTest {
    @Test
    public void whenClientExistInDatabase() throws SQLException {

        final ResultSet set = mock(ResultSet.class);
        when(set.next()).thenReturn(true);
        when(set.getBoolean("exists")).thenReturn(true);

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("exist_client_by_l_p")).thenReturn("mock_script");

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final ClientExistChecker checker =
                new ClientExistChecker(connection, properties);

        final boolean result = checker.clientIsExist("test", "test");

        Assert.assertTrue(result);
        verify(statement).setString(1, "test");
        verify(statement).setString(2, "test");
    }

    @Test
    public void whenClientNotExistInDatabase() throws SQLException {

        final ResultSet set = mock(ResultSet.class);
        when(set.next()).thenReturn(true);
        when(set.getBoolean("exists")).thenReturn(false);

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("exist_client_by_l_p")).thenReturn("mock_script");

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final ClientExistChecker checker =
                new ClientExistChecker(connection, properties);

        final boolean result = checker.clientIsExist("test", "test");

        Assert.assertFalse(result);
        verify(statement).setString(1, "test");
        verify(statement).setString(2, "test");
    }

    @Test
    public void whenConnectionTrowSQLException() throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("exist_client_by_l_p")).thenReturn("mock_script");

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script"))
                .thenThrow(mock(SQLException.class));

        final ClientExistChecker checker =
                new ClientExistChecker(connection, properties);

        final boolean result = checker.clientIsExist("test", "test");

        Assert.assertFalse(result);
    }

    @Test
    public void whenLoginNull() {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        final Connection connection = mock(Connection.class);

        final ClientExistChecker checker =
                new ClientExistChecker(connection, properties);

        final boolean result = checker.clientIsExist(null, "test");
        Assert.assertFalse(result);
    }

    @Test
    public void whenPasswordNull() {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        final Connection connection = mock(Connection.class);

        final ClientExistChecker checker =
                new ClientExistChecker(connection, properties);

        final boolean result = checker.clientIsExist("test", null);
        Assert.assertFalse(result);
    }
}