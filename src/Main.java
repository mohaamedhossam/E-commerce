import config.DatabaseManager;
import controller.CustomerController;
import model.*;
import repository.*;
import service.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) throws SQLException {

        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        ProductRepository productRepository = new ProductRepository();
        ProductService productService = new ProductService(productRepository);

        OrderProductRepository orderProductRepository = new OrderProductRepository();
        OrderProductService orderProductService = new OrderProductService(orderProductRepository);

        OrderRepository orderRepository = new OrderRepository();
        OrderService orderService = new OrderService(orderRepository , orderProductService);

        CustomerRepository customerRepository = new CustomerRepository();
        CustomerService customerService = new CustomerService(customerRepository,orderService);





        ReviewRepository reviewRepository  = new ReviewRepository();
        ReviewService reviewService = new ReviewService(reviewRepository);
        CustomerController customerController = new CustomerController(customerService, orderProductService, orderService, productService, reviewService);


        Product product = new Product(5,"product 5","Category C",800);
        Customer customer = new Customer(4,"Mohamed","Hossam","mohamed@example.com","123456");
        Order order = new Order(4, 3, LocalDate.now(), 0, "12 street,City, Country", LocalDate.now().plusDays(7),0);
        OrderProduct orderProduct = new OrderProduct(1,3,1);
        Review review = new Review(1,1,1,"very nice product , expensive but deserved ");

        try {
            customerController.singUb(customer);
//            System.out.println(customerController.login(6));
//            System.out.println(customerController.viewProducts());
//            customerController.addToCart(6,2,2);
//            customerController.removeFromCart(6,2);
//            System.out.println(customerController.openCart(6));
//            customerController.confirmOrder(6,"testing adress");
//            System.out.println(customerController.searchProducts(null,null,null,0));
        } catch (SQLException e) {
            System.out.println(e);
        }finally {
            DatabaseManager.closeConnection(DatabaseManager.getConnection());
        }

//        Scanner scanner = new Scanner(System.in);
//        int choice;
//        do {
//            System.out.println("Welcome to the Online Shopping System");
//            System.out.println("1. Add Product to Cart");
//            System.out.println("2. Remove Product from Cart");
//            System.out.println("3. View Cart");
//            System.out.println("4. Checkout");
//            System.out.println("0. Exit");
//            System.out.print("Enter your choice: ");
//            choice = scanner.nextInt();
//
//            try {
//
//
//            switch (choice) {
//                case 1:
//
//                    break;
//                case 2:
//
//                    break;
//                case 3:
//
//                    break;
//                case 4:
//
//                    break;
//                case 0:
//                    System.out.println("Bye");
//                    break;
//                default:
//                    System.out.println("Invalid choice. Please try again.");
//            }
//            }catch (SQLException e) {
//                System.out.println(e);
//            }
//        } while (choice != 0);

//        scanner.close();
    }
}