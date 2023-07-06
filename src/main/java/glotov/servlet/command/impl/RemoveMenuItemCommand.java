package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.service.MenuService;
import glotov.servlet.service.impl.MenuServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import static glotov.servlet.util.PageName.ADMIN_MENU_PAGE;
import static glotov.servlet.util.RequestAttributeName.MENU_ITEM_ID;

public class RemoveMenuItemCommand implements Command {
    private final MenuService menuService;
    public RemoveMenuItemCommand() {
        this.menuService = MenuServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int itemId = Integer.parseInt(request.getParameter(MENU_ITEM_ID));
        menuService.removeMenuItem(itemId);
        return ADMIN_MENU_PAGE;
    }
}
