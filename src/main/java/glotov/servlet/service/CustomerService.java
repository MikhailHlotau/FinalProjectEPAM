package glotov.servlet.service;

import glotov.servlet.exception.DaoException;
import glotov.servlet.exception.ServiceException;
import glotov.servlet.model.Customer;
import glotov.servlet.model.Role;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomers() throws ServiceException;
    Customer authenticate(String login, String password) throws ServiceException, DaoException;
    void banCustomer(int customerId) throws ServiceException, DaoException;
    void addBonusPoints(int customerId, int points) throws ServiceException, DaoException;
    void addLoyaltyPoints(int customerId, int points) throws ServiceException, DaoException;

    boolean existsByLogin(String login) throws ServiceException;

}