package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Order {

    private int orderId;
    private int customerId;
    private LocalDate orderDate;
    private int totalAmount;
    private String deliveryAddress;
    private LocalDate expectedDeliveryDate;

    private int confirmed ;


    public Order(int orderId, int customerId, LocalDate orderDate, int totalAmount, String deliveryAddress, LocalDate expectedDeliveryDate, int confirmed) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.deliveryAddress = deliveryAddress;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.confirmed = confirmed;
    }

    public static Order mapRow(ResultSet rs) throws SQLException {
        int orderId = rs.getInt("order_id");
        int customerId = rs.getInt("customer_id");
        LocalDate orderDate = rs.getDate("order_date").toLocalDate();
        int totalAmount = rs.getInt("total_amount");
        String deliveryAddress = rs.getString("delivery_address");
        LocalDate expectedDeliveryDate = rs.getDate("expected_delivery_date").toLocalDate();
        int confirmed = rs.getInt("confirmed");
        return new Order(orderId, customerId, orderDate, totalAmount, deliveryAddress, expectedDeliveryDate,confirmed);
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public LocalDate getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(LocalDate expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }
    public int getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(int confirmed) {
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", expectedDeliveryDate=" + expectedDeliveryDate +
                '}';
    }


}
