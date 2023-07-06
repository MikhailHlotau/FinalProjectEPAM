package glotov.servlet.service;

import glotov.servlet.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();
    boolean authenticate(String userName, String password);
    void banCustomer(int customerId);
    void addBonusPoints(int customerId, int points);
    void addLoyaltyPoints(int customerId, int points);
}