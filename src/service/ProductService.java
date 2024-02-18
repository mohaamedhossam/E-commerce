package service;

import Iservice.IproductService;
import model.Product;
import repository.ProductRepository;

import java.sql.SQLException;
import java.util.List;

public class ProductService implements IproductService {
    private final ProductRepository repo;
    public ProductService (ProductRepository repo){
        this.repo = repo;
    }
    public List<Product> getAllProducts() throws SQLException {
        return repo.getAllProducts();
    }
    public Product getProductById(int id) throws SQLException {
        return repo.getProductById(id);
    }
    public void addProduct(Product product) throws SQLException {
        repo.addProduct(product);
    }
    public void updateProductPrice(int id, int updatedPrice) throws SQLException {
        repo.updateProductPrice(id,updatedPrice);
    }
    public void deleteProduct(int id) throws SQLException {
        repo.deleteProduct(id);
    }
    public List<Product> searchProducts(String keyword, String category, Integer minPrice, Integer maxPrice) throws SQLException {
        if (keyword == null) {
            keyword = "";
        }
        if (category == null) {
            category = "";
        }
        if (minPrice == null || minPrice < 0) {
            minPrice = 0;
        }
        if (maxPrice == null || maxPrice <= 0) {
            maxPrice = Integer.MAX_VALUE;
        }
        return repo.searchProducts(keyword, category, minPrice, maxPrice);
    }

}
