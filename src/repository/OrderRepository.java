package repository;

import config.DatabaseManager;
import model.Order;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderRepository {


    public List<Order> getAllOrders() throws SQLException {
        List<Order> orders = new ArrayList<>();

        ResultSet resultSet = DatabaseManager.getStatement().executeQuery("SELECT * FROM orders");
        while (resultSet.next()) {
            Order order = Order.mapRow(resultSet);
            orders.add(order);
        }

        return orders;
    }

    public Order getOrderById(int id) throws SQLException {
        ResultSet resultSet = DatabaseManager.getStatement().executeQuery("SELECT * FROM orders WHERE order_id = " + id);
        if (resultSet.next()) {
            return Order.mapRow(resultSet);
        } else {
            System.out.println("No order found with id: " + id);
            return null;
        }
    }
    public Order getCurrentCustomerOrder(int customerId) throws SQLException {
        String query = "SELECT * FROM orders WHERE customer_id = ? AND confirmed = 0";
        try (PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, customerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Order.mapRow(resultSet);
            } else {
                System.out.println("No unconfirmed order found for customer id: " + customerId);
                return null;
            }
        }
    }


    public int addOrder(int customer_id) throws SQLException {
        LocalDate today = LocalDate.now();
        String query = "INSERT INTO orders (customer_id, order_date, total_amount, delivery_address, expected_delivery_date) VALUES (?, ?, 0, '', ?)";
        PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1, customer_id);
        preparedStatement.setDate(2, Date.valueOf(today));
        preparedStatement.setDate(3, Date.valueOf(today));

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int orderId = generatedKeys.getInt(1); //the generated order_id
                System.out.println("Order added successfully with id : "+ orderId);
                return orderId;
            }
        }
        throw new SQLException("Failed to add customer or obtain ID.");
    }


    public void updateOrderAmount(int orderId, int totalPrice) throws SQLException {
        Order order = getOrderById(orderId);
        if (order != null) {
            String query = "UPDATE orders SET total_amount = ? WHERE order_id = ?";
            PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, totalPrice);
            preparedStatement.setInt(2, orderId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Order updated successfully");
            } else {
                System.out.println("Failed to update order");
            }
        }
    }

        public void updateOrder(int orderId, String deliveryAddress) throws SQLException {
        LocalDate today = LocalDate.now();
        LocalDate expectedDeliveryDate = today.plusDays(7);

        String query = "UPDATE orders SET delivery_address = ?, order_date = ?, expected_delivery_date = ?, confirmed = ? WHERE order_id = ?";
        PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query);
        preparedStatement.setString(1, deliveryAddress);
        preparedStatement.setDate(2, Date.valueOf(today));
        preparedStatement.setDate(3, Date.valueOf(expectedDeliveryDate));
        preparedStatement.setInt(4, 1);
        preparedStatement.setInt(5, orderId);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Order updated successfully");
        } else {
            System.out.println("Failed to update order");
        }
    }


    public void deleteOrder(int id) throws SQLException {
        Order order = getOrderById(id);
        if (order != null) {
        String query = "DELETE FROM orders WHERE order_id = " + id;
        int rowsAffected = DatabaseManager.getStatement().executeUpdate(query);
        if (rowsAffected > 0) {
            System.out.println("Order deleted successfully");
        } else {
            System.out.println("Failed to delete order");
        }
        }
    }
}
