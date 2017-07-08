package ru.pravvich.servlet;

import ru.pravvich.jdbc.GenericDAO;
import ru.pravvich.jdbc.properties.PropertiesLoader;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Set in servlet context database join.
 */
@WebListener
public class ContextListener implements ServletContextListener {

    private GenericDAO dao;

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        initDAOLayer(servletContextEvent);

    }

    private void initDAOLayer(ServletContextEvent servletContextEvent) {
        try {

            dao = new GenericDAO("login_db.properties",
                    "db_scripts.properties");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        final ServletContext servletContext =
                servletContextEvent.getServletContext();


        servletContext.setAttribute("db", dao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        dao.closeConnection();
    }
}
