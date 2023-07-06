package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.model.MenuItem;
import glotov.servlet.service.MenuService;
import glotov.servlet.service.impl.MenuServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static glotov.servlet.util.PageName.ADMIN_MENU_PAGE;
import static glotov.servlet.util.RequestAttributeName.MENU_ITEM_NAME;
import static glotov.servlet.util.RequestAttributeName.MENU_ITEM_PRICE;

public class AddMenuItemCommand implements Command {
    private final MenuService menuService;

    public AddMenuItemCommand() {
        this.menuService = MenuServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(MENU_ITEM_NAME);
        double price = Double.parseDouble(request.getParameter(MENU_ITEM_PRICE));
        MenuItem menuItem = new MenuItem(name, price);
        menuService.addMenuItem(menuItem);
        return ADMIN_MENU_PAGE;
    }
}
