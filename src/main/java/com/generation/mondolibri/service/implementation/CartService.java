package com.generation.mondolibri.service.implementation;

import com.generation.mondolibri.exceptions.checked.ProductAlreadyInCartException;
import com.generation.mondolibri.exceptions.checked.ProductNotFoundException;
import com.generation.mondolibri.exceptions.checked.UserNotFoundException;
import com.generation.mondolibri.model.entity.Cart;
import com.generation.mondolibri.model.entity.Product;
import com.generation.mondolibri.model.entity.User;

import com.generation.mondolibri.repository.jpa.CartRepository;
import com.generation.mondolibri.repository.jpa.ProductRepository;
import com.generation.mondolibri.repository.jpa.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * Service class for managing operations related to user carts.
 * Extends AbstractService to leverage common transactional and CRUD operations.
 */
@Service
public class CartService extends AbstractService<Cart,Integer> {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;


    @Autowired
    public CartService(CartRepository ca, UserRepository userRepository, ProductRepository productRepository) {
        super(ca);
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    /**
     * Adds a product to the user's cart.
     *
     * @param userId    ID of the user.
     * @param productId ID of the product to add to the cart.
     * @throws IllegalArgumentException if the user or product is not found.
     */
    @Transactional
    public void addProductToCart(Integer userId, Integer productId)
            throws UserNotFoundException, ProductAlreadyInCartException, ProductNotFoundException {
        Cart cart = getOrCreateCart(userId);
        Product product = productRepository.
                    findById(productId).
                    orElseThrow(() -> new ProductNotFoundException("Product not found"));
            for(Product item: cart.getProducts()) {
                if (Objects.equals(item.getId(), product.getId())) {
                    throw new ProductAlreadyInCartException("Product is already in cart" + productId);
                }
            }
            cart.getProducts().add(product);
            repository.save(cart);
    }

    @Transactional
    protected Cart getOrCreateCart(Integer userId) throws UserNotFoundException {
        User user = userRepository.
                findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Cart cart = user.getCart();
        if (cart == null) {
            cart = new Cart();
            cart.setUser(user);
            user.setCart(cart);
            repository.save(cart);
        }
        return cart;
    }

    /**
     * Removes a product from the user's cart.
     *
     * @param userId    ID of the user.
     * @param productId ID of the product to remove from the cart.
     * @throws IllegalArgumentException if the user or product is not found, or if the cart is not found for the user.
     */
    @Transactional
    public void removeProductFromCart(Integer userId, Integer productId)
            throws UserNotFoundException, ProductNotFoundException {
        Cart cart = getOrCreateCart(userId);
        Product product = productRepository
                    .findById(productId)
                    .orElseThrow(() -> new ProductNotFoundException("Product not found"));
            cart.getProducts().remove(product);
            repository.save(cart);
    }

    /**
     * Retrieves the cart of a user by user ID.
     *
     * @param userId ID of the user.
     * @return Cart object associated with the user.
     * @throws IllegalArgumentException if the user is not found.
     */
    @Transactional(readOnly = true)
    public Cart getCartByUserId(Integer userId) throws UserNotFoundException {
        return userRepository
                .findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"))
                .getCart();
    }

    /**
     * Clears the cart of a user by user ID.
     *
     * @param userId ID of the user.
     * @throws IllegalArgumentException if the user is not found.
     */
    @Transactional
    public void clearCart(Integer userId) throws UserNotFoundException {
            Cart cart = getCartByUserId(userId);
            cart.getProducts().clear();
            repository.save(cart);

    }
}


