package service;

import Iservice.IorderProductService;
import model.OrderProduct;
import model.Product;
import repository.OrderProductRepository;
import repository.OrderRepository;
import repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class OrderProductService implements IorderProductService {
    private final OrderProductRepository orderProductRepo;
    public ProductRepository productRepository;
    public OrderRepository orderRepository = new OrderRepository();

    public OrderProductService(OrderProductRepository orderProductRepo) {
        this.orderProductRepo = orderProductRepo;
        productRepository = new ProductRepository();
    }
    public List<OrderProduct> getAllOrderProducts() throws SQLException {
        return orderProductRepo.getAllOrderProducts();
    }
    public List<OrderProduct> getOrderProductsByOrderId(int orderId) throws SQLException {
        return orderProductRepo.getOrderProductsByOrderId(orderId);
    }
    public void addToCart(int customer_id, int productId, int quantity) throws SQLException {
        int orderId = orderRepository.getCurrentCustomerOrder(customer_id).getOrderId();
        if(orderProductRepo.orderProductExists(orderId, productId)){
            System.out.println("it is already exists will plus the quantity ");
            OrderProduct orderProduct = orderProductRepo.getSpecificOrderProduct(orderId,productId);
            orderProductRepo.updateOrderProductQuantity(orderId,productId,orderProduct.getQuantity()+quantity);
        }else {
            OrderProduct orderProduct = new OrderProduct(orderId, productId, quantity);
            orderProductRepo.addOrderProduct(orderProduct);
            computeTotalPriceForOrder(orderId);
        }
    }

    public void updateOrderProductQuantity(int orderId, int productId, int newQuantity) throws SQLException {
        orderProductRepo.updateOrderProductQuantity(orderId, productId, newQuantity);
        computeTotalPriceForOrder(orderId);

    }
    public void removeFromCart(int customer_id, int productId) throws SQLException {
        int orderId = orderRepository.getCurrentCustomerOrder(customer_id).getOrderId();
        orderProductRepo.deleteOrderProduct(orderId, productId);
        computeTotalPriceForOrder(orderId);
    }

    // return el products el fel cart 7alyan
    public List<OrderProduct> openCart(int customer_id) throws SQLException {
        int order_id = orderRepository.getCurrentCustomerOrder(customer_id).getOrderId();
        return getOrderProductsByOrderId(order_id);
    }
    // compute the total price and update it
    public int computeTotalPriceForOrder(int orderId) throws SQLException {
        List<OrderProduct> orderProducts = getOrderProductsByOrderId(orderId);
        int totalPrice = 0;

        for (OrderProduct orderProduct : orderProducts) {
            int productId = orderProduct.getProductId();
            int quantity = orderProduct.getQuantity();
            int price = productRepository.getProductById(productId).getPrice(); // Get the price of the product

            totalPrice += price * quantity;
        }
        orderRepository.updateOrderAmount(orderId,totalPrice);
        return totalPrice;
    }
}
