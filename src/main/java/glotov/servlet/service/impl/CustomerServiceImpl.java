package glotov.servlet.service.impl;

import glotov.servlet.dao.CustomerDao;
import glotov.servlet.dao.impl.CustomerDaoImpl;
import glotov.servlet.model.Customer;
import glotov.servlet.service.CustomerService;
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
    public List<Customer> findAllCustomers() {
        logger.log(Level.INFO, "Выполнен метод findAllCustomers() CustomerServiceImpl");
        return customerDao.findAllCustomers();
    }

    @Override
    public void banCustomer(int customerId) {
        customerDao.banCustomer(customerId);
    }

    @Override
    public void addBonusPoints(int customerId, int points) {
        customerDao.addBonusPoints(customerId, points);
    }

    @Override
    public void addLoyaltyPoints(int customerId, int points) {
        customerDao.addLoyaltyPoints(customerId, points);
    }

    @Override
    public boolean authenticate(String login, String password) {
        logger.log(Level.INFO, "Выполнен метод authenticate() CustomerServiceImpl");
        return customerDao.authenticate(login, password);
    }
}