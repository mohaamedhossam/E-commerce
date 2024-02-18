package repository;

import config.DatabaseManager;
import model.Review;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository {

    public List<Review> getAllReviews() throws SQLException {
        List<Review> reviews = new ArrayList<>();

        ResultSet resultSet = DatabaseManager.getStatement().executeQuery("SELECT * FROM Review");
        while (resultSet.next()) {
            Review review = Review.mapRow(resultSet);
            reviews.add(review);
        }

        return reviews;
    }

    public Review getReviewById(int id) throws SQLException {
        ResultSet resultSet = DatabaseManager.getStatement().executeQuery("SELECT * FROM Review WHERE review_id = " + id);
        if (resultSet.next()) {
            return Review.mapRow(resultSet);
        } else {
            System.out.println("No such Review with this id");
            return null;
        }
    }

    public void addReview(Review review) throws SQLException {
        String query = "INSERT INTO Review (product_id, customer_id, Comment) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, review.getProductID());
        preparedStatement.setInt(2, review.getCustomerID());
        preparedStatement.setString(3, review.getComment());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Review added successfully");
        } else {
            System.out.println("Failed to add review");
        }
    }

    public void deleteReview(int id) throws SQLException {
        Review review = getReviewById(id);
        if (review != null) {
            String query = "DELETE FROM Review WHERE review_id = '" + id + "'";
            int rowsAffected = DatabaseManager.getStatement().executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Review deleted successfully");
            } else {
                System.out.println("Failed to delete review");
            }
        }
    }
}
