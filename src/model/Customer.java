package model;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Customer {
    private int customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public Customer(int customerId, String firstName, String lastName, String email, String password) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public static Customer mapRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("customer_id");
        String firstName = rs.getString("f_name");
        String lastName = rs.getString("l_name");
        String email = rs.getString("mail");
        String password = rs.getString("password");
        return new Customer(id, firstName, lastName, email, password);
    }
    public int getCustomerId() {
        return customerId;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
