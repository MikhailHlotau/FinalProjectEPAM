package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.model.MenuItem;
import glotov.servlet.model.Order;
import glotov.servlet.service.MenuService;
import glotov.servlet.service.OrderService;
import glotov.servlet.service.impl.MenuServiceImpl;
import glotov.servlet.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;

import static glotov.servlet.util.PageName.CUSTOMER_MENU_PAGE;
import static glotov.servlet.util.RequestAttributeName.*;

public class PlaceOrderCommand implements Command {
    private final MenuService menuService;
    private final OrderService orderService;

    public PlaceOrderCommand() {
        this.menuService = MenuServiceImpl.getInstance();
        this.orderService = OrderServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int menuItemId = Integer.parseInt(request.getParameter(MENU_ITEM_ID));
        MenuItem menuItem = menuService.getMenuItemById(menuItemId);
        double totalPrice = menuItem.getPrice();
        LocalDateTime orderTime = LocalDateTime.parse(request.getParameter(ORDER_TIME));
        Order order = new Order(menuItemId, totalPrice, orderTime);
        orderService.placeOrder(order);
        request.setAttribute(ORDER_TOTAL_PRICE, totalPrice);
        return CUSTOMER_MENU_PAGE;
    }
}
