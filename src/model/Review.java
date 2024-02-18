package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Review {

    private int reviewID;
    private int productID;
    private int customerID;
    private String comment;

    public Review(int reviewID, int productID, int customerID, String comment) {
        this.reviewID = reviewID;
        this.productID = productID;
        this.customerID = customerID;
        this.comment = comment;
    }

    public static Review mapRow(ResultSet rs) throws SQLException {
        int reviewID = rs.getInt("review_id");
        int productID = rs.getInt("product_id");
        int customerID = rs.getInt("customer_id");
        String comment = rs.getString("comment");
        return new Review(reviewID, productID, customerID, comment);
    }

    public int getReviewID() {
        return reviewID;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }


    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewID=" + reviewID +
                ", productID=" + productID +
                ", customerID=" + customerID +
                ", comment='" + comment + '\'' +
                '}';
    }
}
