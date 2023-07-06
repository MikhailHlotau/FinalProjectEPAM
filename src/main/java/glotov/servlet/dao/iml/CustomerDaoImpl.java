package glotov.servlet.dao.iml;

import glotov.servlet.connection.AbstractConnection;
import glotov.servlet.dao.CustomerDao;
import glotov.servlet.model.Customer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDaoImpl implements CustomerDao {

    private final Connection connection;

    public CustomerDaoImpl() throws SQLException, ClassNotFoundException {
        this.connection = AbstractConnection.connect();
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customerList = new ArrayList<>();

        try {
            String sql = "SELECT * FROM customers";
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int customerId = resultSet.getInt("customer_id");
                String firstName = resultSet.getString("firstname");
                String lastName  = resultSet.getString("lastName");
                String phone  = resultSet.getString("phone");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                boolean banned = resultSet.getBoolean("banned");
                int bonusPoints = resultSet.getInt("bonusPoints");
                int loyaltyPoints = resultSet.getInt("loyaltyPoints");

                Customer customer = new Customer(customerId,firstName,lastName,phone, email, password, banned, bonusPoints, loyaltyPoints);
                customerList.add(customer);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка ошибок выполнения SQL-запроса
        }

        return customerList;
    }

    @Override
    public void banCustomer(int customerId) {
        try {
            String sql = "UPDATE customers SET banned = true WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, customerId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка ошибок выполнения SQL-запроса
        }
    }

    @Override
    public void addBonusPoints(int customerId, int points) {
        try {
            String sql = "UPDATE customers SET bonus_points = bonus_points + ? WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, points);
            statement.setInt(2, customerId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка ошибок выполнения SQL-запроса
        }
    }

    @Override
    public void addLoyaltyPoints(int customerId, int points) {
        try {
            String sql = "UPDATE customers SET loyalty_points = loyalty_points + ? WHERE customer_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, points);
            statement.setInt(2, customerId);
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
            // Обработка ошибок выполнения SQL-запроса
        }
    }

}