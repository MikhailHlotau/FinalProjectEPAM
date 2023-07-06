package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.service.OrderService;
import glotov.servlet.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import static glotov.servlet.util.PageName.CUSTOMER_MENU_PAGE;
import static glotov.servlet.util.RequestAttributeName.*;

public class CalculatePriceCommand implements Command {
    private final OrderService orderService;
    public CalculatePriceCommand() {
        this.orderService = OrderServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int menuItemId = Integer.parseInt(request.getParameter(MENU_ITEM_ID));
        String orderTime = request.getParameter(ORDER_TIME);
        double totalPrice = orderService.calculatePrice(menuItemId, orderTime);
        request.setAttribute(ORDER_TOTAL_PRICE, totalPrice);
        return CUSTOMER_MENU_PAGE;
    }
}
