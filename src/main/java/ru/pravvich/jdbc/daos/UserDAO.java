package ru.pravvich.jdbc.daos;

import ru.pravvich.jdbc.action.MusicAdder;
import ru.pravvich.jdbc.action.MusicDeleter;
import ru.pravvich.jdbc.action.MusicGetter;
import ru.pravvich.jdbc.action.MusicUpdater;
import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.util.List;

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

    /**
     * Get all musics for Client by id.
     *
     * @param id for matching.
     * @return list musics fir client.
     */
    public List<String> getMusics(final int id) {
        return new MusicGetter(connection, properties).getMusicByClientId(id);
    }

    /**
     * Add music for concrete client.
     *
     * @param id    of Client.
     * @param music for add.
     * @return true if success, else false.
     */
    public boolean addMusic(final int id, final String music) {
        return new MusicAdder(connection, properties).addMusic(id, music);
    }

    /**
     * Delete music for concrete client.
     *
     * @param id    of Client.
     * @param music for delete.
     * @return true if success, else false.
     */
    public boolean deleteMusic(final int id, final String music) {
        return new MusicDeleter(connection, properties).deleteMusic(id, music);
    }

    /**
     * Update music set for client.
     *
     * @param id     of Client.
     * @param musics new list of musicks.
     * @return true if success, else false.
     */
    public boolean updateMusicSet(final int id, final List<String> musics) {
        return new MusicUpdater(connection, properties)
                .updateMusicSet(id, musics);
    }
}
