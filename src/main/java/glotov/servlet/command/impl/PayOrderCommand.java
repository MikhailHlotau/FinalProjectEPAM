package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.service.OrderService;
import glotov.servlet.service.impl.OrderServiceImpl;
import jakarta.servlet.http.HttpServletRequest;

import static glotov.servlet.util.PageName.CUSTOMER_ORDERS_PAGE;
import static glotov.servlet.util.RequestAttributeName.ORDER_ID;

public class PayOrderCommand implements Command {
    private final OrderService orderService;
    public PayOrderCommand() {
        this.orderService = OrderServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        orderService.payOrder(orderId);
        return CUSTOMER_ORDERS_PAGE;
    }
}
