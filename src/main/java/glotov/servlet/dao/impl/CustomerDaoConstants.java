package glotov.servlet.dao.impl;

public class CustomerDaoConstants {
    public static final String SQL_SELECT_ALL_CUSTOMERS = "SELECT * FROM customers";
    public static final String SQL_UPDATE_CUSTOMER_BANNED = "UPDATE customers SET banned = true WHERE customer_id = ?";
    public static final String SQL_UPDATE_CUSTOMER_BONUS_POINTS = "UPDATE customers SET bonus_points = bonus_points + ? WHERE customer_id = ?";
    public static final String SQL_UPDATE_CUSTOMER_LOYALTY_POINTS = "UPDATE customers SET loyalty_points = loyalty_points + ? WHERE customer_id = ?";
    public static final String SQL_SELECT_ALL_CUSTOMERS_LOGIN = "SELECT * FROM customers WHERE login = ?";
    public static final String SQL_SELECT_EXISTING_CUSTOMER_BY_LOGIN = "SELECT COUNT(*) FROM customers WHERE login = ?";
    public static final String SQL_INSERT_CUSTOMER = "INSERT INTO customers (login, first_name, last_name, phone, email, password, role, banned, bonus_points, loyalty_points) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private CustomerDaoConstants() {
    }
}

