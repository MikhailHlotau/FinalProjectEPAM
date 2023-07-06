package glotov.servlet.command.impl;

import glotov.servlet.command.Command;
import glotov.servlet.model.Review;
import glotov.servlet.service.OrderService;
import glotov.servlet.service.ReviewService;
import glotov.servlet.service.impl.OrderServiceImpl;
import glotov.servlet.service.impl.ReviewServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import static glotov.servlet.util.PageName.CUSTOMER_ORDERS_PAGE;
import static glotov.servlet.util.RequestAttributeName.*;

public class LeaveReviewCommand implements Command {
    private final OrderService orderService;
    private final ReviewService reviewService;

    public LeaveReviewCommand() {
        this.orderService = OrderServiceImpl.getInstance();
        this.reviewService = ReviewServiceImpl.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request) {
        int orderId = Integer.parseInt(request.getParameter(ORDER_ID));
        String comment = request.getParameter(REVIEW_COMMENT);
        int rating = Integer.parseInt(request.getParameter(REVIEW_RATING));
        Review review = new Review(orderId, comment, rating);
        reviewService.leaveReview(review);
        orderService.setOrderReviewed(orderId);
        return CUSTOMER_ORDERS_PAGE;
    }
}
