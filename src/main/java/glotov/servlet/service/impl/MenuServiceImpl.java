package glotov.servlet.service.impl;

import glotov.servlet.dao.CustomerDao;
import glotov.servlet.dao.MenuDao;
import glotov.servlet.dao.impl.CustomerDaoImpl;
import glotov.servlet.dao.impl.MenuDaoImpl;
import glotov.servlet.exception.DaoException;
import glotov.servlet.exception.ServiceException;
import glotov.servlet.model.MenuItem;
import glotov.servlet.service.MenuService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class MenuServiceImpl implements MenuService {


    private static final Logger logger = LogManager.getLogger();
    private static final MenuServiceImpl instance = new MenuServiceImpl();

    private final MenuDao menuDao;

    private MenuServiceImpl() {
        this.menuDao = MenuDaoImpl.getInstance();
    }
    public static MenuServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<MenuItem> getAllMenuItems() throws ServiceException {
        try {
            return menuDao.getAllMenuItems();
        } catch (DaoException e) {
            logger.log(Level.INFO, "Error getting a list of all menuItems in the database", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public MenuItem getMenuItemById(int id) {
        return menuDao.getMenuItemById(id);
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {
        menuDao.addMenuItem(menuItem);
    }

    @Override
    public void removeMenuItem(int id) {
        menuDao.removeMenuItem(id);
    }
}
