package ru.pravvich.jdbc;

import ru.pravvich.jdbc.action.MusicAdder;
import ru.pravvich.jdbc.action.MusicDeleter;
import ru.pravvich.jdbc.action.MusicGetter;
import ru.pravvich.jdbc.action.MusicUpdater;
import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;

/**
 * Determines available for user role actions.
 */
public class UserDAO {
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
    public UserDAO(final java.sql.Connection connection,
                   final PropertiesLoader properties) {

        this.connection = connection;
        this.properties = properties;
    }

    public String[] getMusics(final int id) {
        return new MusicGetter(connection, properties).getMusicByClientId(id);
    }

    public boolean addMusic(final int id, final String music) {
        return new MusicAdder(connection, properties).addMusic(id, music);
    }

    public boolean deleteMusic(final int id, final String music) {
        return new MusicDeleter(connection, properties).deleteMusic(id, music);
    }

    public boolean updateMusicSet(final int id, final String[] musics) {
        return new MusicUpdater(connection, properties)
                .updateMusicSet(id, musics);
    }
}
