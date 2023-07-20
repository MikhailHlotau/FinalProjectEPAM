package glotov.servlet.service;

import glotov.servlet.exception.DaoException;
import glotov.servlet.exception.ServiceException;
import glotov.servlet.model.MenuItem;

import java.util.List;

public interface MenuService {
    List<MenuItem> getAllMenuItems() throws ServiceException, DaoException;
    MenuItem getMenuItemById(int id);
    void addMenuItem(MenuItem menuItem);
    void removeMenuItem(int id);
}
