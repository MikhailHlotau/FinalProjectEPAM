package glotov.servlet.model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int customerId;
    private int menuItemId;
    private double totalPrice;
    private boolean cancelled;
    private boolean reviewed;
    private int rating;
    private boolean paid;
    private LocalDateTime orderTime;

    public Order(int menuItemId, double totalPrice, LocalDateTime orderTime) {
        this.menuItemId = menuItemId;
        this.totalPrice = totalPrice;
        this.orderTime = orderTime;
        this.cancelled = false;
        this.reviewed = false;
        this.rating = 0;
        this.paid = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
    }

    public boolean isCancelled() {
        return cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    public boolean isReviewed() {
        return reviewed;
    }

    public void setReviewed(boolean reviewed) {
        this.reviewed = reviewed;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}