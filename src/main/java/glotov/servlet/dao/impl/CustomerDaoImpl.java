package glotov.servlet.dao.impl;

import glotov.servlet.connection.ConnectionPool;
import glotov.servlet.dao.CustomerDao;
import glotov.servlet.dao.mapper.CustomerMapper;
import glotov.servlet.model.Customer;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    private static Logger logger = LogManager.getLogger();
    private final Connection connection;
    private final PreparedStatement getAllCustomersStatement;
    private final PreparedStatement banCustomerStatement;
    private final PreparedStatement addBonusPointsStatement;
    private final PreparedStatement addLoyaltyPointsStatement;
    private final PreparedStatement authenticateStatement;
    private final CustomerMapper customerMapper;
    private static CustomerDaoImpl instance = new CustomerDaoImpl();

    private CustomerDaoImpl() {
        connection = ConnectionPool.getInstance().getConnection();
        logger.log(Level.INFO, "Создан объект CustomerServiceImpl");
        try {
            getAllCustomersStatement = connection.prepareStatement("SELECT * FROM customers");
            banCustomerStatement = connection.prepareStatement("UPDATE customers SET banned = true WHERE customer_id = ?");
            addBonusPointsStatement = connection.prepareStatement("UPDATE customers SET bonus_points = bonus_points + ? WHERE customer_id = ?");
            addLoyaltyPointsStatement = connection.prepareStatement("UPDATE customers SET loyalty_points = loyalty_points + ? WHERE customer_id = ?");
            authenticateStatement = connection.prepareStatement("SELECT password FROM customers WHERE first_name = ?");
            customerMapper = new CustomerMapper();
        } catch (SQLException e) {
            logger.error("Failed to prepare statements: " + e.getMessage());
            throw new RuntimeException("Failed to prepare statements", e);
        }
    }

    public static CustomerDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Customer> findAllCustomers() {
        List<Customer> customerList = new ArrayList<>();

        try {
            ResultSet resultSet = getAllCustomersStatement.executeQuery();

            while (resultSet.next()) {
                Customer customer = customerMapper.mapCustomer(resultSet);
                customerList.add(customer);
            }
            logger.log(Level.INFO, "Выполнен метод findAllCustomers() CustomerDaoImpl");

            resultSet.close();
        } catch (SQLException e) {
            logger.error("Failed to execute SQL query: " + e.getMessage());
            throw new RuntimeException("Failed to execute SQL query", e);
        }

        return customerList;
    }

    @Override
    public void banCustomer(int customerId) {
        try {
            banCustomerStatement.setInt(1, customerId);
            banCustomerStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to execute SQL query: " + e.getMessage());
            throw new RuntimeException("Failed to execute SQL query", e);
        }
    }

    @Override
    public void addBonusPoints(int customerId, int points) {
        try {
            addBonusPointsStatement.setInt(1, points);
            addBonusPointsStatement.setInt(2, customerId);
            addBonusPointsStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to execute SQL query: " + e.getMessage());
            throw new RuntimeException("Failed to execute SQL query", e);
        }
    }

    @Override
    public void addLoyaltyPoints(int customerId, int points) {
        try {
            addLoyaltyPointsStatement.setInt(1, points);
            addLoyaltyPointsStatement.setInt(2, customerId);
            addLoyaltyPointsStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Failed to execute SQL query: " + e.getMessage());
            throw new RuntimeException("Failed to execute SQL query", e);
        }
    }

    @Override
    public boolean authenticate(String login, String password) {
        try {
            authenticateStatement.setString(1, login);
            try (ResultSet resultSet = authenticateStatement.executeQuery()) {
                if (resultSet.next()) {
                    String passwordFromDataBase = resultSet.getString(1);
                    return password.equals(passwordFromDataBase);
                }
                logger.log(Level.INFO, "Выполнен метод authenticate() CustomerDaoImpl");
            }
        } catch (SQLException e) {
            logger.error("Failed to execute SQL query: " + e.getMessage());
            throw new RuntimeException("Failed to execute SQL query", e);
        }
        return false;
    }
}