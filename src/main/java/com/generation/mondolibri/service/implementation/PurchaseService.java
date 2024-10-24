package com.generation.mondolibri.service.implementation;

import com.generation.mondolibri.exceptions.checked.NotEnoughStockException;
import com.generation.mondolibri.model.entity.Product;
import com.generation.mondolibri.model.entity.Purchase;
import com.generation.mondolibri.model.entity.User;
import com.generation.mondolibri.repository.jpa.CartRepository;
import com.generation.mondolibri.repository.jpa.ProductRepository;
import com.generation.mondolibri.repository.jpa.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Service class for managing operations related to purchases.
 * Extends AbstractService to leverage common transactional and CRUD operations.
 */
@Service
public class PurchaseService extends AbstractService<Purchase,Integer> {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;


    @Autowired
    public PurchaseService(PurchaseRepository repository, CartRepository cartRepository, ProductRepository productRepository) {
        super(repository);
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Transactional(rollbackFor = NotEnoughStockException.class)
    public Purchase createPurchase(User user, Product product) throws NotEnoughStockException {
        validateStock(product);
        Purchase purchase = createAndSavePurchase(user, product);
        updateProductStock(product);
        removeFromUserCart(user, product);
        return purchase;
    }

    private void validateStock(Product product) throws NotEnoughStockException {
        if (product.getQuantity() < 1) {
            throw new NotEnoughStockException("Not enough stock for product: " + product.getName());
        }
    }

    @Transactional
    protected Purchase createAndSavePurchase(User user, Product product) {
        Purchase purchase = new Purchase(new Date(), product, user);
        return repository.save(purchase);
    }

    @Transactional
    protected void updateProductStock(Product product) {
        product.setQuantity(product.getQuantity() - 1);
        productRepository.save(product);
    }

    @Transactional
    protected void removeFromUserCart(User user, Product product) {
        cartRepository
                .findByUser(user)
                .ifPresent(cart -> {
                    cart.getProducts().remove(product);
                    cartRepository.save(cart);
                });
    }
}
