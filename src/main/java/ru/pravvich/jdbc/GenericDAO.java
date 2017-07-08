package ru.pravvich.jdbc;

import ru.pravvich.jdbc.connection.DatabaseConnection;
import ru.pravvich.jdbc.daos.AdminDAO;
import ru.pravvich.jdbc.daos.ModeratorDAO;
import ru.pravvich.jdbc.daos.UserDAO;
import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Access to singletons for DAOs each roles .
 */
public class GenericDAO implements DAO {

    private static final Object lock = new Object();

    private final PropertiesLoader properties;

    private static ModeratorDAO moderatorDAO;
    private static AdminDAO adminDAO;
    private static UserDAO userDAO;

    private Connection connection;

    public GenericDAO(final String dbAuth, final String dbScript)
            throws SQLException {

        properties = new PropertiesLoader(dbScript);
        initConnection(dbAuth);
    }

    private void initConnection(final String dbAuth) throws SQLException {
        connection = new DatabaseConnection(
                new PropertiesLoader(dbAuth)).getConnection();
    }

    @Override
    public AdminDAO getAdminDAO() {
        AdminDAO dao = adminDAO;
        if (dao == null) {
            synchronized (lock) {
                dao = adminDAO;
                if (dao == null) {
                    adminDAO = new AdminDAO(connection, properties);
                    return adminDAO;
                }
            }
        }

        return dao;
    }

    @Override
    public UserDAO getUserDAO() {
        UserDAO dao = userDAO;
        if (dao == null) {
            synchronized (lock) {
                dao = userDAO;
                if (dao == null) {
                    userDAO = new UserDAO(connection, properties);
                    return userDAO;
                }
            }
        }

        return dao;
    }

    @Override
    public ModeratorDAO getModeratorDAO() {
        ModeratorDAO dao = moderatorDAO;
        if (dao == null) {
            synchronized (lock) {
                dao = moderatorDAO;
                if (dao == null) {
                    moderatorDAO = new ModeratorDAO(connection, properties);
                    return moderatorDAO;
                }
            }
        }

        return dao;
    }

    public void closeConnection() {
        try {

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
