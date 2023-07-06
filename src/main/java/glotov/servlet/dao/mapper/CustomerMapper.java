package glotov.servlet.dao.mapper;

import glotov.servlet.model.Customer;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper {
    public Customer mapCustomer(ResultSet resultSet) throws SQLException {
        int customerId = resultSet.getInt("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        boolean banned = resultSet.getBoolean("banned");
        int bonusPoints = resultSet.getInt("bonus_points");
        int loyaltyPoints = resultSet.getInt("loyalty_points");

        return new Customer.Builder()
                .setId(customerId)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setPassword(password)
                .setBanned(banned)
                .setBonusPoints(bonusPoints)
                .setLoyaltyPoints(loyaltyPoints)
                .build();
    }
}
