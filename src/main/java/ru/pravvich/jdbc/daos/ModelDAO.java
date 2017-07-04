package ru.pravvich.jdbc.daos;

import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;

/**
 * Model DAO layer. Determines default constructor for all DAOs.
 */
public class ModelDAO {
    /**
     * Connection to database.
     */
    protected final Connection connection;
    /**
     * Requests loader from file Properties.
     */
    protected final PropertiesLoader properties;

    /**
     * Default constructor.
     *
     * @param connection to database.
     * @param properties requests loader from file Properties.
     */
    public ModelDAO(final java.sql.Connection connection,
                   final PropertiesLoader properties) {

        this.connection = connection;
        this.properties = properties;
    }
}
