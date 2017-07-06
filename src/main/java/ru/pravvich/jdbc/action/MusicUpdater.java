package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.util.List;

/**
 * Created by pavel on 03.07.17.
 */
public class MusicUpdater extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public MusicUpdater(final Connection connection, final PropertiesLoader scripts) {
        super(connection, scripts);
    }

    public boolean updateMusicSet(final int id, final List<String> musics) {
        return false;
    }
}
