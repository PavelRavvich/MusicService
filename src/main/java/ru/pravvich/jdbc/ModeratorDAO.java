package ru.pravvich.jdbc;

import ru.pravvich.jdbc.action.ClientGetterAddress;
import ru.pravvich.jdbc.action.ClientGetterMusicType;
import ru.pravvich.jdbc.action.ClientGetterRole;
import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Client;

/**
 * Created by pavel on 03.07.17.
 */
public class ModeratorDAO extends UserDAO {
    /**
     * Default constructor.
     *
     * @param connection to database.
     * @param properties requests loader from file Properties.
     */
    public ModeratorDAO(final java.sql.Connection connection,
                        final PropertiesLoader properties) {

        super(connection, properties);
    }

    public Client[] getUsersByAddress(final String address) {
        return new ClientGetterAddress(connection, properties)
                .getUsersByAddress(address);
    }

    public Client[] getUsersByRole(final String role) {
        return new ClientGetterRole(connection, properties)
                .getClientsByRole(role);
    }

    public Client[] getUsersByMusicType(final String musicType) {
        return new ClientGetterMusicType(connection, properties)
                .getUsersByMusicType(musicType);
    }
}
