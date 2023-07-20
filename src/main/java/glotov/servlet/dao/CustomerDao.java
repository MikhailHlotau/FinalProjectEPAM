package glotov.servlet.dao;

import glotov.servlet.exception.DaoException;
import glotov.servlet.model.Customer;
import glotov.servlet.model.Role;

import java.util.List;

public interface CustomerDao {
    List<Customer> findAllCustomers() throws DaoException;
    Customer authenticate(String login, String password) throws DaoException;
    void banCustomer(int customerId) throws DaoException;
    void addBonusPoints(int customerId, int points) throws DaoException;
    void addLoyaltyPoints(int customerId, int points) throws DaoException;
    boolean existsByLogin(String login) throws DaoException;
    void createCustomer(Customer customer) throws DaoException;
}
