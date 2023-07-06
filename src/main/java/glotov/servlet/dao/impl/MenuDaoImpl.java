package glotov.servlet.dao.impl;

import glotov.servlet.dao.MenuDao;
import glotov.servlet.model.MenuItem;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuDaoImpl implements MenuDao {

    private final Map<Integer, MenuItem> menuItems;

    public MenuDaoImpl() {
        this.menuItems = new HashMap<>();
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return new ArrayList<>(menuItems.values());
    }

    @Override
    public MenuItem getMenuItemById(int id) {
        return menuItems.get(id);
    }

    @Override
    public void addMenuItem(MenuItem menuItem) {
        int id = generateMenuItemId();
        menuItem.setId(id);
        menuItems.put(id, menuItem);
    }

    @Override
    public void removeMenuItem(int id) {
        menuItems.remove(id);
    }

    private int generateMenuItemId() {
        // Генерация уникального идентификатора для нового элемента меню
        // В реальной реализации может быть использовано другое решение, например, база данных
        return menuItems.size() + 1;
    }
}
