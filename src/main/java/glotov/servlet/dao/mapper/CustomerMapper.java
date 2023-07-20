package glotov.servlet.dao.mapper;

import glotov.servlet.model.Customer;
import glotov.servlet.model.Role;
import java.sql.ResultSet;
import java.sql.SQLException;;

public class CustomerMapper implements Mapper<Customer> {
    @Override
    public Customer map(ResultSet resultSet) throws SQLException {
        int customerId = resultSet.getInt("customer_id");
        String login = resultSet.getString("login");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        Role role = Role.valueOf(resultSet.getString("role"));
        boolean banned = resultSet.getBoolean("banned");
        int bonusPoints = resultSet.getInt("bonus_points");
        int loyaltyPoints = resultSet.getInt("loyalty_points");

        return new Customer.CustomerBuilder()
                .withId(customerId)
                .withLogin(login)
                .withFirstName(firstName)
                .withLastName(lastName)
                .withPhone(phone)
                .withEmail(email)
                .withRole(role)
                .withBanned(banned)
                .withBonusPoints(bonusPoints)
                .withLoyaltyPoints(loyaltyPoints)
                .build();
    }
}