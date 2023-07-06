package glotov.servlet.service.impl;

import glotov.servlet.dao.ReviewDao;
import glotov.servlet.dao.iml.ReviewDaoImpl;
import glotov.servlet.model.Review;
import glotov.servlet.service.ReviewService;


public class ReviewServiceImpl implements ReviewService {

    private static final ReviewServiceImpl instance = new ReviewServiceImpl();
    private final ReviewDao reviewDao;

    private ReviewServiceImpl() {
        this.reviewDao = new ReviewDaoImpl();
    }

    public static ReviewServiceImpl getInstance() {
        return instance;
    }

    @Override
    public void leaveReview(Review review) {
        reviewDao.leaveReview(review);
    }
}
