package repository;

import config.DatabaseManager;
import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {


    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customers = new ArrayList<>();

        ResultSet resultSet = DatabaseManager.getStatement().executeQuery("SELECT * FROM customer");
        while (resultSet.next()) {
            Customer customer = Customer.mapRow(resultSet);
            customers.add(customer);
        }
        return customers;
    }

    public Customer getCustomerById(int id) throws SQLException {

        ResultSet resultSet = DatabaseManager.getStatement().executeQuery("SELECT * FROM customer WHERE customer_id = " + id);
        if (resultSet.next()) {
            return Customer.mapRow(resultSet);
        } else {
            System.out.println("No such customer with this id");
            return null;
        }
    }

    public int addCustomer(Customer customer) throws SQLException {
        String query = "INSERT INTO customer (f_name, l_name, mail, password) VALUES (?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, customer.getFirstName());
            preparedStatement.setString(2, customer.getLastName());
            preparedStatement.setString(3, customer.getEmail());
            preparedStatement.setString(4, customer.getPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    System.out.println("Customer added successfully with id : "+id);
                    return id; // da el customer_id el database 3amaleto
                }
            }
        }
        throw new SQLException("Failed to add customer or obtain ID.");
    }


    public void updateCustomerPassword(int id, String newPassword) throws SQLException {
        Customer customer = getCustomerById(id);
        if (customer != null) {
            String query = "UPDATE customer SET password = '" + newPassword + "' WHERE customer_id = " + id;
            int rowsAffected = DatabaseManager.getStatement().executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Customer password updated successfully");
            } else {
                System.out.println("Failed to update customer password");
            }
        }
    }

    public void deleteCustomer(int id) throws SQLException {

        Customer customer = getCustomerById(id);
        if (customer != null) {
            String query = "DELETE FROM customer WHERE customer_id = " + id;
            int rowsAffected = DatabaseManager.getStatement().executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Customer deleted successfully");
            } else {
                System.out.println("Failed to delete customer");
            }
        }
    }
}
