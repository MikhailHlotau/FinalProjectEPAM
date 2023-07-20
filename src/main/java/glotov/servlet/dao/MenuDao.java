package glotov.servlet.dao;

import glotov.servlet.exception.DaoException;
import glotov.servlet.model.MenuItem;
import java.util.List;

public interface MenuDao {
    List<MenuItem> getAllMenuItems() throws DaoException;
    MenuItem getMenuItemById(int id);
    void addMenuItem(MenuItem menuItem);
    void removeMenuItem(int id);
}
