package ru.pravvich.jdbc.action;

import ru.pravvich.jdbc.properties.PropertiesLoader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Getter for music types by client id..
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

    /**
     * Get for music types by client id..
     */
    public List<String> getMusicByClientId(final int id) {

        final List<String> musics = new ArrayList<>();

        try (final PreparedStatement statement = connection

                .prepareStatement(scripts.get("get_music_types_by_user_id"))) {


            statement.setInt(1, id);

            final ResultSet set = statement.executeQuery();

            while (set.next()) {
                musics.add(set.getString(1));
            }

            return musics;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return musics;
    }
}
