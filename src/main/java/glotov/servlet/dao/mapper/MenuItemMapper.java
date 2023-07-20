package glotov.servlet.dao.mapper;

import glotov.servlet.model.MenuItem;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MenuItemMapper implements Mapper<MenuItem> {
    @Override
    public MenuItem map(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("menu_item_id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        double price = resultSet.getDouble("price");

        MenuItem menuItem = new MenuItem.MenuItemBuilder()
                .withId(id)
                .withName(name)
                .withDescription(description)
                .withPrice(price)
                .build();

        return menuItem;
    }
}

