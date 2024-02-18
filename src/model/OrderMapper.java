package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper {

    private int orderId;
    private int customerId;

    public OrderMapper(int orderId, int customerId) {
        this.orderId = orderId;
        this.customerId = customerId;
    }


    public static OrderMapper mapRow(ResultSet rs) throws SQLException {
        int orderId = rs.getInt("order_id");
        int customerId = rs.getInt("customer_id");
        return new OrderMapper(orderId, customerId);
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

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                '}';
    }
}
