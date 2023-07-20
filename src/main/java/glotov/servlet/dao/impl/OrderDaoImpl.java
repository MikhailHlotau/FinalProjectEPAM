package glotov.servlet.dao.impl;

import glotov.servlet.dao.OrderDao;
import glotov.servlet.model.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {


    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public List<Order> getCustomerOrders(int customerId) {
        return null;
    }

    @Override
    public void placeOrder(Order order) {

    }

    @Override
    public void cancelOrder(int orderId) {

    }

    @Override
    public void setOrderReviewed(int orderId) {

    }

    @Override
    public void rateOrder(int orderId, int rating) {

    }

    @Override
    public void payOrder(int orderId) {

    }

    @Override
    public double calculatePrice(int menuItemId, String orderTime) {
        return 0;
    }
}
