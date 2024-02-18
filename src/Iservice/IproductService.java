package Iservice;

import model.Product;

import java.sql.SQLException;
import java.util.List;

public interface IproductService {

    public List<Product> getAllProducts() throws SQLException;

    public Product getProductById(int id) throws SQLException;

    public void addProduct(Product product) throws SQLException;

    public void updateProductPrice(int id, int updatedPrice) throws SQLException;

    public void deleteProduct(int id) throws SQLException;

    public List<Product> searchProducts(String keyword, String category, Integer minPrice, Integer maxPrice) throws SQLException;
}