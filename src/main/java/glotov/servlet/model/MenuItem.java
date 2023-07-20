package glotov.servlet.model;


public class MenuItem {
    private int id;
    private String name;
    private String description;
    private double price;

    private MenuItem() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public static class MenuItemBuilder {
        private MenuItem menuItem;

        public MenuItemBuilder() {
            menuItem = new MenuItem();
        }

        public MenuItemBuilder withId(int id) {
            menuItem.id = id;
            return this;
        }

        public MenuItemBuilder withName(String name) {
            menuItem.name = name;
            return this;
        }

        public MenuItemBuilder withDescription(String description) {
            menuItem.description = description;
            return this;
        }

        public MenuItemBuilder withPrice(double price) {
            menuItem.price = price;
            return this;
        }

        public MenuItem build() {
            return menuItem;
        }
    }
}