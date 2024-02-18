package service;

import Iservice.IcustomerService;
import model.Customer;
import repository.CustomerRepository;
import repository.OrderRepository;

import java.sql.SQLException;
import java.util.List;

public class CustomerService implements IcustomerService {
    private final CustomerRepository customerRepo;
    private final OrderService orderService ;

    public CustomerService(CustomerRepository customerRepo , OrderService orderService) {
        this.customerRepo = customerRepo;
        this.orderService = orderService ;
    }
    public List<Customer> getAllCustomers() throws SQLException {
        return customerRepo.getAllCustomers();
    }
    public Customer getCustomerById(int id) throws SQLException {
        return customerRepo.getCustomerById(id);
    }
    public int addCustomer(Customer customer) throws SQLException {
        int generatedCustomerId = customerRepo.addCustomer(customer);
        orderService.addOrder(generatedCustomerId);
        return generatedCustomerId ;

    }
    public void updateCustomerPassword(int id, String newPassword) throws SQLException {
        customerRepo.updateCustomerPassword(id,newPassword);
    }
    public void deleteCustomer(int id) throws SQLException {
        customerRepo.deleteCustomer(id);
    }
}
