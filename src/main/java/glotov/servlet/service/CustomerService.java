package glotov.servlet.service;

import glotov.servlet.exception.ServiceException;
import glotov.servlet.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers() throws ServiceException;
    boolean authenticate(String login, String password) throws ServiceException;
    void banCustomer(int customerId) throws ServiceException;
    void addBonusPoints(int customerId, int points) throws ServiceException;
    void addLoyaltyPoints(int customerId, int points) throws ServiceException;
}