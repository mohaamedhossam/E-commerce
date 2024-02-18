package Iservice;

import model.Customer;

import java.sql.SQLException;
import java.util.List;

public interface IcustomerService {

    public List<Customer> getAllCustomers() throws SQLException ;
    public Customer getCustomerById(int id) throws SQLException ;
    public int addCustomer(Customer customer) throws SQLException ;
    public void updateCustomerPassword(int id, String newPassword) throws SQLException ;
    public void deleteCustomer(int id) throws SQLException ;
}
