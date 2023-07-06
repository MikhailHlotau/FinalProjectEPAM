package glotov.servlet.dao.iml;

import glotov.servlet.dao.OrderDao;
import glotov.servlet.model.Order;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDaoImpl implements OrderDao {

    private final Map<Integer, Order> orders;

    public OrderDaoImpl() {
        this.orders = new HashMap<>();
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public List<Order> getCustomerOrders(int customerId) {
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : orders.values()) {
            if (order.getCustomerId() == customerId) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    @Override
    public void placeOrder(Order order) {
        int id = generateOrderId();
        order.setId(id);
        orders.put(id, order);
    }

    @Override
    public void cancelOrder(int orderId) {
        orders.get(orderId).setCancelled(true);
    }

    @Override
    public void setOrderReviewed(int orderId) {
        orders.get(orderId).setReviewed(true);
    }

    @Override
    public void rateOrder(int orderId, int rating) {
        orders.get(orderId).setRating(rating);
    }

    @Override
    public void payOrder(int orderId) {
        orders.get(orderId).setPaid(true);
    }

    @Override
    public double calculatePrice(int menuItemId, String orderTime) {
        // Реализация расчета стоимости заказа
        // Возвращаемая стоимость может быть вычислена на основе различных факторов,
        // таких как выбранные позиции меню, время заказа и другие параметры
        return 0.0;
    }

    private int generateOrderId() {
        // Генерация уникального идентификатора для нового заказа
        // В реальной реализации может быть использовано другое решение, например, база данных
        return orders.size() + 1;
    }
}
