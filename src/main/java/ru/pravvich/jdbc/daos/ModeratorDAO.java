package ru.pravvich.jdbc.daos;

import ru.pravvich.jdbc.action.*;
import ru.pravvich.jdbc.properties.PropertiesLoader;
import ru.pravvich.model.Address;
import ru.pravvich.model.Client;

import java.util.List;

/**
 * Created by pavel on 03.07.17.
 */
public class ModeratorDAO extends ModelDAO {
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

    /**
     * Get all users which contain Address.
     *
     * @param address for matching.
     * @return list of clients with same Address.
     */
    public List<Client> getUsersByAddress(final Address address) {
        return new ClientGetterAddress(connection, properties)
                .getUsersByAddress(address);
    }

    /**
     * Get all Clients which have param role.
     *
     * @param role for find.
     * @return list Clients which have param role.
     */
    public List<Client> getClientsByRole(final String role) {
        return new ClientGetterRole(connection, properties)
                .getClientsByRole(role);
    }

    /**
     * Get all clients which link to param musicType.
     *
     * @param musicType for matching.
     * @return list of Clients which link to param musicType.
     */
    public List<Client> getClientsByMusicType(final String musicType) {
        return new ClientGetterMusicType(connection, properties)
                .getClientsByMusicType(musicType);
    }

    public Client getClientLogPass(final String login, final String password) {
        return new Client();
    }

    /**
     * Get role id by pair login & password.
     *
     * @param login of Client.
     * @param password of Client.
     * @return id of role. If Client not found return flag 0.
     * If login or password == null return flag 0;
     */
    public int getAccessLogPass(final String login, final String password) {
        return new RoleIdGetter(connection, properties)
                .getRoleIdByLogPas(login, password);
    }

    public boolean clientIsExist(final String login, final String password) {
        return new ClientExistChecker(connection, properties)
                .clientIsExist(login, password);
    }
}
