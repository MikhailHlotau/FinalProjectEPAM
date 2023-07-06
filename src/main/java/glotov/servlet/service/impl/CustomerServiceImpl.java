package glotov.servlet.service.impl;

import glotov.servlet.dao.CustomerDao;
import glotov.servlet.dao.impl.CustomerDaoImpl;
import glotov.servlet.model.Customer;
import glotov.servlet.service.CustomerService;
import java.sql.SQLException;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {

    private static final CustomerServiceImpl instance;

    static {
        try {
            instance = new CustomerServiceImpl();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private final CustomerDao customerDao;

    private CustomerServiceImpl() throws SQLException, ClassNotFoundException {
        this.customerDao = new CustomerDaoImpl();
    }
    public static CustomerServiceImpl getInstance() {
        return instance;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerDao.getAllCustomers();
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
    public boolean authenticate(String userName, String password){
        return userName.equals(password);
    }
}
