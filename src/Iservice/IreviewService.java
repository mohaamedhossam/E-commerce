package Iservice;

import model.Review;

import java.sql.SQLException;
import java.util.List;

public interface IreviewService {

    public List<Review> getAllReviews() throws SQLException ;

    public Review getReviewById(int id) throws SQLException ;

    public void addReview(Review review) throws SQLException ;

    public void deleteReview(int id) throws SQLException ;
}
