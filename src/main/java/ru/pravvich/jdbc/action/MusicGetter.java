package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.util.List;

/**
 * Created by pavel on 03.07.17.
 */
public class MusicGetter extends Action {
    /**
     * Default constructor for all actions with database.
     *
     * @param connection to database.
     * @param scripts    which extract from properties file.
     */
    public MusicGetter(final Connection connection, final PropertiesLoader scripts) {
        super(connection, scripts);
    }

    public List<String> getMusicByClientId(final int id) {
        return null;
    }
}
