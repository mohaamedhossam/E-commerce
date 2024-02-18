package repository;

import config.DatabaseManager;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {


    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();

        ResultSet resultSet = DatabaseManager.getStatement().executeQuery("SELECT * FROM product");
        while (resultSet.next()) {
            Product product = Product.mapRow(resultSet);
            products.add(product);
        }

        return products;
    }
    public Product getProductById(int id) throws SQLException {
        ResultSet resultSet = DatabaseManager.getStatement().executeQuery("SELECT * FROM product WHERE product_id = " + id);
        if (resultSet.next()) {
            return Product.mapRow(resultSet);
        } else {
            System.out.println("No such Product with this id");
            return null;
        }
    }
    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO product (product_id, name, category, price) VALUES (?, ?, ?, ?)";
        PreparedStatement preparedStatement = DatabaseManager.getConnection().prepareStatement(query);
        preparedStatement.setInt(1, product.getId());
        preparedStatement.setString(2, product.getName());
        preparedStatement.setString(3, product.getCategory());
        preparedStatement.setInt(4, product.getPrice());

        int rowsAffected = preparedStatement.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Product added successfully");
        } else {
            System.out.println("Failed to add product");
        }
    }


    public void updateProductPrice(int id, int updatedPrice) throws SQLException {
        Product product = getProductById(id);
        if (product != null) {
            product.setPrice(updatedPrice);
            String query = "UPDATE product SET price = '" + updatedPrice + "' WHERE product_id = '" + id + "'";
            int rowsAffected = DatabaseManager.getStatement().executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Price updated successfully");
            } else {
                System.out.println("Failed to update price");
            }
        }
    }

    public void deleteProduct(int id) throws SQLException {
        Product product = getProductById(id);
        if (product != null) {
            String query = "DELETE FROM product WHERE product_id = '" + id + "'";
            int rowsAffected = DatabaseManager.getStatement().executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully");
            } else {
                System.out.println("Failed to delete product");
            }
        }
    }

    // estakhdemt hena Integer 3ashan a2dar ahoto b null lw msh 3aiz el filter da
    public List<Product> searchProducts(String keyword, String category, Integer minPrice, Integer maxPrice) throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product WHERE name LIKE '%"+keyword+"%' AND category LIKE '%"+category+"%' AND price BETWEEN "+minPrice+" AND "+maxPrice;

        ResultSet resultSet = DatabaseManager.getStatement().executeQuery(query);
        while (resultSet.next()) {
            Product product = Product.mapRow(resultSet);
            products.add(product);
        }
        return products;
    }


}
