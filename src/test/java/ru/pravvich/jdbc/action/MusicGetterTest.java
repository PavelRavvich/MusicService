package ru.pravvich.jdbc.action;

import org.junit.Assert;
import org.junit.Test;
import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Address;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MusicGetterTest {

    @Test
    public void whenSelectOneMusicType() throws SQLException {
        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("get_music_types_by_user_id"))
                .thenReturn("mock_script");

        final ResultSet set = mock(ResultSet.class);
        when(set.getString(1)).thenReturn("jazz");
        when(set.next()).thenReturn(true, false);


        final PreparedStatement statement = mock(PreparedStatement.class);
        when(statement.executeQuery()).thenReturn(set);

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script")).thenReturn(statement);

        final MusicGetter getter =
                new MusicGetter(connection, properties);


        final List<String> result = getter.getMusicByClientId(1);

        Assert.assertThat(result.get(0), is("jazz"));
    }

    @Test
    public void whenConnectionThrowException() throws SQLException {

        final PropertiesLoader properties = mock(PropertiesLoader.class);
        when(properties.get("get_music_types_by_user_id"))
                .thenReturn("mock_script");

        final Connection connection = mock(Connection.class);
        when(connection.prepareStatement("mock_script"))
                .thenThrow(mock(SQLException.class));

        final MusicGetter getter =
                new MusicGetter(connection, properties);


        final List<String> result = getter.getMusicByClientId(1);

        Assert.assertTrue(result.isEmpty());
    }
}