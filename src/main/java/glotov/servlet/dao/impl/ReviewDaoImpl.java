package glotov.servlet.dao.impl;

import glotov.servlet.dao.ReviewDao;
import glotov.servlet.model.Review;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl implements ReviewDao {

    private final List<Review> reviews;

    public ReviewDaoImpl() {
        this.reviews = new ArrayList<>();
    }

    @Override
    public void leaveReview(Review review) {
        reviews.add(review);
    }
}
