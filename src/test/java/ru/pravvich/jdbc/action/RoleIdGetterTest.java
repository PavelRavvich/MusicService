package ru.pravvich.jdbc.action;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.*;

public class RoleIdGetterTest {

    @Test
    public void whenClientExistWithRoleId1() throws SQLException {

        final ResultSet set = mock(ResultSet.class);
        when(set.next()).thenReturn(true);
        when(set.getInt("role")).thenReturn(1);

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("get_role")).thenReturn("mock_script");

        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final RoleIdGetter getter = new RoleIdGetter(connection, properties);
        final int result = getter.getRoleIdByLogPas("test", "test");

        Assert.assertThat(result, is(1));
        verify(statement, times(1)).setString(1, "test");
        verify(statement, times(1)).setString(2, "test");
    }

    @Test
    public void whenLoginIsNullThenReturn0() {

        final Connection connection = mock(Connection.class);
        final PropertiesLoader properties = mock(PropertiesLoader.class);

        final RoleIdGetter getter = new RoleIdGetter(connection, properties);
        final int result = getter.getRoleIdByLogPas(null, "test");

        Assert.assertThat(result, is(0));
    }

    @Test
    public void whenPasswordIsNullThenReturn0() {

        final Connection connection = mock(Connection.class);
        final PropertiesLoader properties = mock(PropertiesLoader.class);

        final RoleIdGetter getter = new RoleIdGetter(connection, properties);
        final int result = getter.getRoleIdByLogPas("test", null);

        Assert.assertThat(result, is(0));
    }

    @Test
    public void whenConnectionThrowSQLException() throws SQLException {
        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("get_role")).thenReturn("mock_script");

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script"))
                .thenThrow(mock(SQLException.class));

        final RoleIdGetter getter = new RoleIdGetter(connection, properties);
        final int result = getter.getRoleIdByLogPas("test", null);

        Assert.assertThat(result, is(0));
    }
}