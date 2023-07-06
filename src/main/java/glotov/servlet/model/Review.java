package glotov.servlet.model;

public class Review {
    private int orderId;
    private String comment;
    private int rating;

    public Review(int orderId, String comment, int rating) {
        this.orderId = orderId;
        this.comment = comment;
        this.rating = rating;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}