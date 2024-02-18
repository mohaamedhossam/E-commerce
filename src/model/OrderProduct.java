package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderProduct {
    private int orderId;
    private int productId;
    private int quantity;

    public OrderProduct(int orderId, int productId, int quantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public static OrderProduct mapRow(ResultSet rs) throws SQLException {
        int orderId = rs.getInt("order_id");
        int productId = rs.getInt("product_id");
        int quantity = rs.getInt("quantity");
        return new OrderProduct(orderId, productId, quantity);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", quantity=" + quantity +
                '}';
    }

}
