package ru.pravvich.jdbc;

import ru.pravvich.jdbc.daos.AdminDAO;
import ru.pravvich.jdbc.daos.ModeratorDAO;
import ru.pravvich.jdbc.daos.UserDAO;

/**
 * Created by pavel on 04.07.17.
 */
public interface DAO {
    UserDAO getUserDAO();
    AdminDAO getAdminDAO();
    ModeratorDAO getModeratorDAO();
}
