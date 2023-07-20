package glotov.servlet.service.impl;

import glotov.servlet.dao.CustomerDao;
import glotov.servlet.dao.impl.CustomerDaoImpl;
import glotov.servlet.exception.DaoException;
import glotov.servlet.exception.ServiceException;
import glotov.servlet.model.Customer;
import glotov.servlet.service.CustomerService;
import glotov.servlet.util.PasswordHasher;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private static final Logger logger = LogManager.getLogger();
    private static final CustomerServiceImpl instance = new CustomerServiceImpl();

    private final CustomerDao customerDao;

    private CustomerServiceImpl() {
        this.customerDao = CustomerDaoImpl.getInstance();
    }

    public static CustomerServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Customer> findAllCustomers() throws ServiceException {
        try {
            return customerDao.findAllCustomers();
        } catch (DaoException e) {
            logger.log(Level.INFO, "Error getting a list of all users in the database", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void banCustomer(int customerId) throws DaoException {
        customerDao.banCustomer(customerId);
    }

    @Override
    public void addBonusPoints(int customerId, int points) throws DaoException {
        customerDao.addBonusPoints(customerId, points);
    }

    @Override
    public void addLoyaltyPoints(int customerId, int points) throws DaoException {
        customerDao.addLoyaltyPoints(customerId, points);
    }

    @Override
    public boolean existsByLogin(String login) throws ServiceException {
        boolean match;
        try {
            match = customerDao.existsByLogin(login);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "Error checking if the user exists in the database", e);
            throw new ServiceException(e);
        }
        return match;
    }


    public void registerCustomer(Customer customer) throws ServiceException {
        try {
                String password = customer.getPassword();
                String hashedPassword = PasswordHasher.encoding(password);
                customer.setPassword(hashedPassword);
                customerDao.createCustomer(customer);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "User registration error", e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Customer authenticate(String login, String password) throws ServiceException {
        String hashedPassword = PasswordHasher.encoding(password);
        try {
            return customerDao.authenticate(login, hashedPassword);
        } catch (DaoException e) {
            logger.log(Level.ERROR, "User authentication error", e);
            throw new ServiceException(e);
        }
    }
}