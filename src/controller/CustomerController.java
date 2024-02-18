package controller;

import Iservice.*;
import model.Customer;
import model.OrderProduct;
import model.Product;
import model.Review;
import repository.CustomerRepository;
import repository.OrderProductRepository;
import service.*;

import java.sql.SQLException;
import java.util.List;

public class CustomerController {
    private IcustomerService customerService;
    private IorderProductService orderProductService;
    private IorderService orderService;
    private IproductService productService;
    private IreviewService reviewService ;

    public CustomerController(CustomerService cs, OrderProductService ops, OrderService os, ProductService ps, ReviewService rs) {
        this.customerService = cs;
        this.orderProductService = ops;
        this.orderService = os;
        this.productService = ps;
        this.reviewService = rs;

    }

    public void singUb(Customer customer) throws SQLException{
        int customer_id = customerService.addCustomer(customer);
    }

    public Customer login(int customer_id) throws SQLException {
        return customerService.getCustomerById(customer_id);
    }

    public List<Product> viewProducts() throws SQLException {
        return productService.getAllProducts();
    }

    public void addToCart(int customer_id, int productId, int quantity) throws SQLException {
        orderProductService.addToCart(customer_id, productId, quantity);
    }

    public void removeFromCart(int customer_id, int productId) throws SQLException {
        orderProductService.removeFromCart(customer_id, productId);
    }

    public List<OrderProduct> openCart(int customer_id) throws SQLException {
        return orderProductService.openCart(customer_id);
    }

    public void confirmOrder(int customer_id, String deliveryAddress) throws SQLException {
        orderService.confirmOrder(customer_id, deliveryAddress);
    }

    public void reviewProduct(Review review) throws SQLException {
        reviewService.addReview(review);
    }
    public List<Product> searchProducts(String keyword, String category, Integer minPrice, Integer maxPrice)throws SQLException {
        return productService.searchProducts(keyword,category,minPrice,maxPrice);
    }
}
