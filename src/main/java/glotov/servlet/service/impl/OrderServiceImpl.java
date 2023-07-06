package glotov.servlet.service.impl;

import glotov.servlet.dao.OrderDao;
import glotov.servlet.dao.impl.OrderDaoImpl;
import glotov.servlet.model.Order;
import glotov.servlet.service.OrderService;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private static final OrderServiceImpl instance = new OrderServiceImpl();
    private final OrderDao orderDao;

    private OrderServiceImpl() {
        this.orderDao = new OrderDaoImpl();
    }

    public static OrderServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }

    @Override
    public List<Order> getCustomerOrders(int customerId) {
        return orderDao.getCustomerOrders(customerId);
    }

    @Override
    public void placeOrder(Order order) {
        orderDao.placeOrder(order);
    }

    @Override
    public void cancelOrder(int orderId) {
        orderDao.cancelOrder(orderId);
    }

    @Override
    public void setOrderReviewed(int orderId) {
        orderDao.setOrderReviewed(orderId);
    }

    @Override
    public void rateOrder(int orderId, int rating) {
        orderDao.rateOrder(orderId, rating);
    }

    @Override
    public void payOrder(int orderId) {
        orderDao.payOrder(orderId);
    }

    @Override
    public double calculatePrice(int menuItemId, String orderTime) {
        return orderDao.calculatePrice(menuItemId, orderTime);
    }
}
