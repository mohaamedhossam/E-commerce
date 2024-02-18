package Iservice;

import model.OrderProduct;

import java.sql.SQLException;
import java.util.List;

public interface IorderProductService {

    public List<OrderProduct> getAllOrderProducts() throws SQLException ;
    public List<OrderProduct> getOrderProductsByOrderId(int orderId) throws SQLException ;
    public List<OrderProduct> openCart(int customer_id) throws SQLException ;
    public void addToCart(int customer_id, int productId, int quantity) throws SQLException ;
    public void updateOrderProductQuantity(int orderId, int productId, int newQuantity) throws SQLException ;
    public void removeFromCart(int customer_id, int productId) throws SQLException ;

    // compute the total price and update it
    public int computeTotalPriceForOrder(int orderId) throws SQLException ;
}
