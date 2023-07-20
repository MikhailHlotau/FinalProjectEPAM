package glotov.servlet.dao.impl;

import glotov.servlet.connection.ConnectionPool;
import glotov.servlet.dao.MenuDao;
import glotov.servlet.dao.mapper.MenuItemMapper;
import glotov.servlet.exception.DaoException;
import glotov.servlet.model.MenuItem;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl implements MenuDao {

    private static final Logger logger = LogManager.getLogger();

    private static final MenuItemMapper menuMapper = new MenuItemMapper();
    private static final MenuDaoImpl instance = new MenuDaoImpl();

    private MenuDaoImpl() {
    }

    public static MenuDaoImpl getInstance() {
        return instance;
    }


    @Override
    public List<MenuItem> getAllMenuItems() throws DaoException {
        List<MenuItem> menuItems = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement getAllCustomersStatement = connection.prepareStatement("SELECT * FROM MenuItems");
             ResultSet resultSet = getAllCustomersStatement.executeQuery()) {
            while (resultSet.next()) {
                MenuItem menuItem = menuMapper.map(resultSet);
                menuItems.add(menuItem);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to execute SQL query: ", e);
            throw new DaoException(e);
        }
        return menuItems;
    }

    @Override
    public MenuItem getMenuItemById(int id) {
        return null;
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {

    }

    @Override
    public void removeMenuItem(int id) {

    }
}
