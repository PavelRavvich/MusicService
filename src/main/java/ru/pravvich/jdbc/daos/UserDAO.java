package ru.pravvich.jdbc.daos;

import ru.pravvich.jdbc.action.MusicAdder;
import ru.pravvich.jdbc.action.MusicDeleter;
import ru.pravvich.jdbc.action.MusicGetter;
import ru.pravvich.jdbc.action.MusicUpdater;
import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;

/**
 * Determines available for user role actions.
 */
public class UserDAO extends ModelDAO {
    /**
     * Default constructor.
     *
     * @param connection to database.
     * @param properties requests loader from file Properties.
     */
    public UserDAO(Connection connection, PropertiesLoader properties) {
        super(connection, properties);
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
