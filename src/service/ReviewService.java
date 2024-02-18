package service;

import Iservice.IreviewService;
import model.Review;
import repository.ReviewRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReviewService implements IreviewService {
    private final ReviewRepository reviewRepo;

    public ReviewService(ReviewRepository reviewRepo){
        this.reviewRepo = reviewRepo;
    }

    public List<Review> getAllReviews() throws SQLException {
        return reviewRepo.getAllReviews();
    }

    public Review getReviewById(int id) throws SQLException {
        return reviewRepo.getReviewById(id);
    }

    public void addReview(Review review) throws SQLException {
        reviewRepo.addReview(review);
    }

    public void deleteReview(int id) throws SQLException {
       reviewRepo.deleteReview(id);
    }
}
