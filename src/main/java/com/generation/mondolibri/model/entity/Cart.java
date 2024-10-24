package com.generation.mondolibri.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Represents a cart entity associated with a user and containing products.
 * This class is mapped to a database table using JPA annotations.
 */
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "id_user")
    private User user; // User associated with this cart

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "id_cart"),
            inverseJoinColumns = @JoinColumn(name = "id_product"))
    private List<Product> products; // List of products in the cart

    /**
     * Default constructor required by Hibernate.
     */
    public Cart() {
    }

    /**
     * Constructs a Cart object with specified products and user.
     *
     * @param products List of products in the cart.
     * @param user     User associated with the cart.
     */
    public Cart(List<Product> products, User user) {
        this.products = products;
        this.user = user;
    }

    /**
     * Constructs a Cart object associated with a user without any products initially.
     *
     * @param user User associated with the cart.
     */
    public Cart(User user) {
        this.user = user;
    }

    /**
     * Retrieves the list of products in the cart.
     *
     * @return List of products in the cart.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets the list of products in the cart.
     *
     * @param products List of products to set in the cart.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Retrieves the user associated with the cart.
     *
     * @return User associated with the cart.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user associated with the cart.
     *
     * @param user User to associate with the cart.
     */
    public void setUser(User user) {
        this.user = user;
    }
}
