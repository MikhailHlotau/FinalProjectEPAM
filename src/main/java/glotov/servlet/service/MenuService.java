package glotov.servlet.service;

import glotov.servlet.model.MenuItem;

import java.util.List;

public interface MenuService {
    List<MenuItem> getAllMenuItems();
    MenuItem getMenuItemById(int id);
    void addMenuItem(MenuItem menuItem);
    void removeMenuItem(int id);
}
