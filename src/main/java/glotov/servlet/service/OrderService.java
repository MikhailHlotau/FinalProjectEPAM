package glotov.servlet.service;

import glotov.servlet.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    List<Order> getCustomerOrders(int customerId);
    void placeOrder(Order order);
    void cancelOrder(int orderId);
    void setOrderReviewed(int orderId);
    void rateOrder(int orderId, int rating);
    void payOrder(int orderId);
    double calculatePrice(int menuItemId, String orderTime);
}
