package glotov.servlet.dao;

import glotov.servlet.model.Customer;
import java.util.List;

public interface CustomerDao {
    List<Customer> getAllCustomers();
    void banCustomer(int customerId);
    void addBonusPoints(int customerId, int points);
    void addLoyaltyPoints(int customerId, int points);
}
