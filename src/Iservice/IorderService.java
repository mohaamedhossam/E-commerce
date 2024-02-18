package Iservice;

import model.Order;

import java.sql.SQLException;
import java.util.List;

public interface IorderService {

    public List<Order> getAllOrders() throws SQLException ;
    public Order getOrderById(int id) throws SQLException ;
    public int addOrder(int customer_id) throws SQLException ;
    public void updateOrderAmount(int orderId, int totalPrice) throws SQLException ;

    public void confirmOrder(int customer_id, String deliveryAddress) throws SQLException ;
    public void deleteOrder(int id) throws SQLException ;
    public int getCurrentCustomerOrder(int customer_id) throws SQLException;
}
