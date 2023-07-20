package glotov.servlet.dao.impl;

import glotov.servlet.exception.DaoException;
import glotov.servlet.connection.ConnectionPool;
import glotov.servlet.dao.CustomerDao;
import glotov.servlet.dao.mapper.CustomerMapper;
import glotov.servlet.model.Customer;
import glotov.servlet.model.Role;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static glotov.servlet.dao.impl.CustomerDaoConstants.*;


public class CustomerDaoImpl implements CustomerDao {
    private static final Logger logger = LogManager.getLogger();
    private static final CustomerMapper customerMapper = new CustomerMapper();
    private static final CustomerDaoImpl instance = new CustomerDaoImpl();

    private CustomerDaoImpl() {
    }

    public static CustomerDaoImpl getInstance() {
        return instance;
    }

    @Override
    public List<Customer> findAllCustomers() throws DaoException {
        List<Customer> customerList = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement getAllCustomersStatement = connection.prepareStatement(SQL_SELECT_ALL_CUSTOMERS);
             ResultSet resultSet = getAllCustomersStatement.executeQuery()) {
            while (resultSet.next()) {
                Customer customer = customerMapper.map(resultSet);
                customerList.add(customer);
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to execute SQL query: ", e);
            throw new DaoException(e);
        }

        return customerList;
    }

    @Override
    public void banCustomer(int customerId) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement banCustomerStatement = connection.prepareStatement(SQL_UPDATE_CUSTOMER_BANNED)) {
            banCustomerStatement.setInt(1, customerId);
            banCustomerStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to execute SQL query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void addBonusPoints(int customerId, int points) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement addBonusPointsStatement = connection.prepareStatement(SQL_UPDATE_CUSTOMER_BONUS_POINTS)) {
            addBonusPointsStatement.setInt(1, points);
            addBonusPointsStatement.setInt(2, customerId);
            addBonusPointsStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to execute SQL query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public void addLoyaltyPoints(int customerId, int points) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement addLoyaltyPointsStatement = connection.prepareStatement(SQL_UPDATE_CUSTOMER_LOYALTY_POINTS)) {
            addLoyaltyPointsStatement.setInt(1, points);
            addLoyaltyPointsStatement.setInt(2, customerId);
            addLoyaltyPointsStatement.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to execute SQL query: ", e);
            throw new DaoException(e);
        }
    }

    @Override
    public Customer authenticate(String login, String password) throws DaoException {
        Customer customer = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement authenticateStatement = connection.prepareStatement(SQL_SELECT_ALL_CUSTOMERS_LOGIN);
        ) {
            authenticateStatement.setString(1, login);
            try (ResultSet resultSet = authenticateStatement.executeQuery()) {
                if (resultSet.next()) {
                    String passwordFromDataBase = resultSet.getString("password");
                    if (password.equals(passwordFromDataBase)) {
                        customer = customerMapper.map(resultSet);
                    }
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR,"Failed to execute SQL query: ", e);
            throw new DaoException("Failed to execute SQL query", e);
        }
        return customer;
    }



    public boolean existsByLogin(String login) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_SELECT_EXISTING_CUSTOMER_BY_LOGIN)) {
            statement.setString(1, login);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Failed to execute SQL query: ", e);
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public void createCustomer(Customer customer) throws DaoException {
        try (Connection connection = ConnectionPool.getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(SQL_INSERT_CUSTOMER, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customer.getLogin());
            statement.setString(2, customer.getFirstName());
            statement.setString(3, customer.getLastName());
            statement.setString(4, customer.getPhone());
            statement.setString(5, customer.getEmail());
            statement.setString(6, customer.getPassword());
            statement.setString(7, String.valueOf(customer.getRole()));
            statement.setBoolean(8, customer.isBanned());
            statement.setInt(9, customer.getBonusPoints());
            statement.setInt(10, customer.getLoyaltyPoints());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                logger.log(Level.INFO, "User creation failed, no row was changed: {}", customer.getLogin());
            } else {
                logger.log(Level.INFO, "User successfully created: {}", customer.getLogin());
            }

        } catch (SQLException e) {
            logger.log(Level.ERROR, "Error creating user", e);
            throw new DaoException(e);
        }
    }
}