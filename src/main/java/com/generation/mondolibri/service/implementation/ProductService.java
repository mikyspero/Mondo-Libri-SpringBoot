package com.generation.mondolibri.service.implementation;

import com.generation.mondolibri.exceptions.checked.ProductAlreadyExists;
import com.generation.mondolibri.exceptions.checked.ProductNotFoundException;
import com.generation.mondolibri.model.entity.Cart;
import com.generation.mondolibri.model.entity.Product;
import com.generation.mondolibri.model.entity.User;

import com.generation.mondolibri.repository.jpa.CartRepository;
import com.generation.mondolibri.repository.jpa.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service class that provides business logic operations related to products in the system.
 * It manages CRUD operations for products, handles product availability updates, and product searches.
 */
@Service
public class ProductService extends AbstractService<Product, Integer> {

    private final CartRepository cartRepository; // Data Access Object for Cart entities


    @Autowired
    public ProductService(ProductRepository productRepository,
                          CartRepository cartRepository) {
        super(productRepository);
        this.cartRepository = cartRepository;
    }

    @Transactional()
    public Product createProduct(Product product) throws ProductAlreadyExists {
        ((ProductRepository) repository)
                .findByName(product.getName())
                .ifPresent(p -> {
                    throw new RuntimeException(new ProductAlreadyExists(p.getName() + " is already in the DB"));
                });

        return repository.save(product);
    }


    /**
     * Updates an existing product in the database.
     * @param product Updated Product object.
     * @param user User performing the operation.
     * @return The updated Product object.
     */
    @Transactional
    public Product updateProduct(Product product, User user) throws ProductNotFoundException {
            Product existingProduct = repository.findById(product.getId()).orElse(null);
            if (existingProduct == null) {
                throw new ProductNotFoundException("Product not found");
            }
            return repository.save(product);
    }

    /**
     * Deletes a product from the database.
     * @param productId ID of the product to be deleted.
     */
    @Transactional
    public void deleteProduct(Integer productId) throws ProductNotFoundException {
            Product product = repository.findById(productId)
                    .orElseThrow(()->new ProductNotFoundException("Product not found"));
            List<Cart> allCarts = cartRepository.findAll();
            for (Cart cart : allCarts) {
                cart.getProducts().remove(product);
                cartRepository.save(cart);
            }
         repository.delete(product);
    }

    /**
     * Searches for products in the database by name using a keyword.
     * @param keyword Keyword to search for in product names.
     * @return List of Product objects matching the search criteria.
     */
    @Transactional
    protected List<Product> search(String keyword) {
        return ((ProductRepository) repository)
                .find(keyword);
    }

    @Transactional
    protected List<Product> search(String keyword, Double minPrice, Double maxPrice) {
        return ((ProductRepository) repository).find(keyword, minPrice, maxPrice);

    }

    /**
     * Searches for products in the database by price range.
     * @param minPrice Minimum price of the products to search for.
     * @param maxPrice Maximum price of the products to search for.
     * @return List of Product objects within the specified price range.
     */
    @Transactional
    protected List<Product> search(Double minPrice, Double maxPrice) {
        return ((ProductRepository) repository).find( minPrice, maxPrice);
    }

    // Helper method to perform product search
    @Transactional
    public List<Product> searchProducts(String keyword, Double minPrice, Double maxPrice) {
        boolean hasKeyword = keyword != null && !keyword.isEmpty();
        boolean hasPriceRange = minPrice != null && maxPrice != null;

        if (hasKeyword && hasPriceRange) {
            return this.search(keyword, minPrice, maxPrice);
        } else if (hasKeyword) {
            return this.search(keyword);
        } else if (hasPriceRange) {
            return this.search(minPrice, maxPrice);
        } else {
            return repository.findAll();
        }
    }

    /**
     * Updates the availability (quantity) of a product after a purchase.
     * @param productId ID of the product to update.
     * @param quantitySold Quantity of the product sold in the transaction.
     */
    @Transactional
    public void updateProductAvailability(Integer productId, int quantitySold) throws ProductNotFoundException {
            Product product = repository.findById(productId)
                    .orElseThrow(()->new ProductNotFoundException("Product not found"));
            int newQuantity = product.getQuantity() - quantitySold;
            if (newQuantity < 0) {
                throw new IllegalStateException("Not enough products in stock");
            }
            product.setQuantity(newQuantity);
            repository.save(product);
    }

    public Product retrieveById(Integer productId) throws ProductNotFoundException {
        return repository.findById(productId)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }
}

