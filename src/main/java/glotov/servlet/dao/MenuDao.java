package glotov.servlet.dao;

import glotov.servlet.model.MenuItem;
import java.util.List;

public interface MenuDao {
    List<MenuItem> getAllMenuItems();
    MenuItem getMenuItemById(int id);
    void addMenuItem(MenuItem menuItem);
    void removeMenuItem(int id);
}
