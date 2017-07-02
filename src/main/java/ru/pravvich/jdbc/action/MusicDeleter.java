package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;

/**
 * Created by pavel on 03.07.17.
 */
public class MusicDeleter extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public MusicDeleter(Connection connection, PropertiesLoader scripts) {
        super(connection, scripts);
    }

    public boolean deleteMusic(final int userID, final String music) {
        return false;
    }
}
