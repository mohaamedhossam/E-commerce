package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.DatabaseManager;
import model.OrderProduct;

public class OrderProductRepository {

    public List<OrderProduct> getAllOrderProducts() throws SQLException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String query = "SELECT * FROM order_product";
        ResultSet resultSet = DatabaseManager.getStatement().executeQuery(query);
        while (resultSet.next()) {
            OrderProduct orderProduct = OrderProduct.mapRow(resultSet);
            orderProducts.add(orderProduct);
        }
        return orderProducts;
    }

    public List<OrderProduct> getOrderProductsByOrderId(int orderId) throws SQLException {
        List<OrderProduct> orderProducts = new ArrayList<>();
        String query = "SELECT * FROM order_product WHERE order_id = " + orderId;
        ResultSet resultSet = DatabaseManager.getStatement().executeQuery(query);
        while (resultSet.next()) {
            OrderProduct orderProduct = OrderProduct.mapRow(resultSet);
            orderProducts.add(orderProduct);
        }
        return orderProducts;
    }
    public OrderProduct getSpecificOrderProduct(int orderId, int productId) throws SQLException {
        String query = "SELECT * FROM order_product WHERE order_id = " + orderId + " AND product_id = " + productId;
        ResultSet resultSet = DatabaseManager.getStatement().executeQuery(query);
        if (resultSet.next()) {
            return OrderProduct.mapRow(resultSet);
        } else {
            System.out.println("No such OrderProduct with this ids");
            return null;
        }
    }
    public boolean orderProductExists(int orderId, int productId) throws SQLException {
        String query = "SELECT 1 FROM order_product WHERE order_id = "+orderId+" AND product_id ="+productId+" LIMIT 1";
        
        ResultSet resultSet = DatabaseManager.getStatement().executeQuery(query);
        return resultSet.next();
    }


    public void addOrderProduct(OrderProduct orderProduct) throws SQLException {
        String query = "INSERT INTO order_product (order_id, product_id, quantity) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, orderProduct.getOrderId());
        preparedStatement.setInt(2, orderProduct.getProductId());
        preparedStatement.setInt(3, orderProduct.getQuantity());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Order product added successfully");
        } else {
            System.out.println("Failed to add order product");
        }
    }

    public void updateOrderProductQuantity(int orderId, int productId, int newQuantity) throws SQLException {
        String query = "UPDATE order_product SET quantity = ? WHERE order_id = ? AND product_id = ?";
        PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, newQuantity);
        preparedStatement.setInt(2, orderId);
        preparedStatement.setInt(3, productId);

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Order product quantity updated successfully");
        } else {
            System.out.println("Failed to update order product quantity");
        }
    }

    public void deleteOrderProduct(int orderId, int productId) throws SQLException {
        String query = "DELETE FROM order_product WHERE order_id = " + orderId + " AND product_id = " + productId;
        int rowsAffected = DatabaseManager.getStatement().executeUpdate(query);
        if (rowsAffected > 0) {
            System.out.println("Order product deleted successfully");
        } else {
            System.out.println("Failed to delete order product");
        }
    }
}
