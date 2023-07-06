package glotov.servlet.service.impl;

import glotov.servlet.dao.MenuDao;
import glotov.servlet.dao.iml.MenuDaoImpl;
import glotov.servlet.model.MenuItem;
import glotov.servlet.service.MenuService;
import java.util.List;

public class MenuServiceImpl implements MenuService {

    private static final MenuServiceImpl instance = new MenuServiceImpl();

    private final MenuDao menuDao;

    private MenuServiceImpl() {
        this.menuDao = new MenuDaoImpl();
    }
    public static MenuServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        return menuDao.getAllMenuItems();
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
