package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.exception.DaoException;
import glotov.servlet.exception.ServiceException;
import glotov.servlet.model.MenuItem;
import glotov.servlet.service.CustomerService;
import glotov.servlet.service.impl.CustomerServiceImpl;
import glotov.servlet.service.impl.MenuServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.util.List;
import static glotov.servlet.util.PageName.LIST_ALL_MENU_ITEMS;
import static glotov.servlet.util.RequestAttributeName.MENU_ITEMS;

public class FindAllMenuItemsCommand implements Command {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public String execute(HttpServletRequest request) {
        MenuServiceImpl menuService = MenuServiceImpl.getInstance();
        List<MenuItem> menuItems;
        try {
            menuItems = menuService.getAllMenuItems();

        } catch (ServiceException e) {
            logger.log(Level.ERROR, e);
            throw new RuntimeException(e);
        }
        request.setAttribute(MENU_ITEMS, menuItems);
        return LIST_ALL_MENU_ITEMS;
    }
}
