package service;

import Iservice.IorderService;
import model.Order;
import repository.OrderProductRepository;
import repository.OrderRepository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class OrderService implements IorderService {
    private final OrderRepository orderRepo;
    private final OrderProductService orderProductService;
    public OrderService(OrderRepository orderRepo, OrderProductService orderProductService) {
        this.orderRepo = orderRepo;
        this.orderProductService = orderProductService;
    }
    public List<Order> getAllOrders() throws SQLException {
        return orderRepo.getAllOrders();
    }
    public Order getOrderById(int id) throws SQLException {
        return orderRepo.getOrderById(id);
    }
    public int addOrder(int customer_id) throws SQLException {
       return orderRepo.addOrder(customer_id);
    }
    public void updateOrderAmount(int orderId, int totalPrice) throws SQLException {
        orderRepo.updateOrderAmount(orderId,totalPrice);
    }

    public void confirmOrder(int customer_id, String deliveryAddress) throws SQLException {
        int orderId = orderRepo.getCurrentCustomerOrder(customer_id).getOrderId();
        if(!orderProductService.getOrderProductsByOrderId(orderId).isEmpty()) {
            orderRepo.updateOrder(orderId, deliveryAddress);
            System.out.println("Here is you order Details" + getOrderById(orderId));
            orderRepo.addOrder(6);

        }else {
            System.out.println("cannot confirm order without products");
        }
    }
    public void deleteOrder(int id) throws SQLException {
        orderRepo.deleteOrder(id);
    }
    // return the order id of the current customer order (cart)
    public int getCurrentCustomerOrder(int customer_id) throws SQLException {
        Order order = orderRepo.getCurrentCustomerOrder(customer_id);
        return order.getOrderId();
    }
}
