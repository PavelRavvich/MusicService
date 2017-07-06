package ru.pravvich.jdbc.connection;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnectionTest {

    private Connection connection;

    @Before
    public void openConnection() throws SQLException {
        connection = new DatabaseConnection(
                new PropertiesLoader("login_db.properties"))
                .getConnection();
    }

    @After
    public void closeConnection() throws SQLException {
        if (!connection.isClosed()){
            connection.close();
        }
    }

    @Test
    public void whenConnectionOpenThenReturnTrue() throws SQLException {
        final boolean result = connection.isClosed();
        Assert.assertFalse(result);
    }
}