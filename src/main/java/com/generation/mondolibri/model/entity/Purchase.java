package com.generation.mondolibri.model.entity;

import jakarta.persistence.*;
import java.util.Date;

/**
 * Entity class representing a purchase made by a user for a specific product.
 * Each Purchase is associated with a User, a Product, and a purchase date.
 */
@Entity
@Table(name = "purchase")
public class Purchase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;  // User who made the purchase

    @ManyToOne
    @JoinColumn(name = "id_product")
    private Product product;  // Product that was purchased

    @Temporal(TemporalType.DATE)
    @Column(name = "purchase_date")
    private Date purchaseDate;  // Date when the purchase was made

    /**
     * Default constructor required by Hibernate.
     * Initializes an empty instance of Purchase.
     */
    public Purchase() {
    }

    /**
     * Constructor that accepts all fields of the Purchase.
     * @param purchaseDate Date when the purchase was made.
     * @param product Product that was purchased.
     * @param user User who made the purchase.
     */
    public Purchase(Date purchaseDate, Product product, User user) {
        this.purchaseDate = purchaseDate;
        this.product = product;
        this.user = user;
    }

    /**
     * Retrieves the date when the purchase was made.
     * @return Date when the purchase was made.
     */
    public Date getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the date when the purchase was made.
     * @param purchaseDate Date when the purchase was made.
     */
    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    /**
     * Retrieves the product that was purchased.
     * @return Product that was purchased.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets the product that was purchased.
     * @param product Product that was purchased.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Retrieves the user who made the purchase.
     * @return User who made the purchase.
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the user who made the purchase.
     * @param user User who made the purchase.
     */
    public void setUser(User user) {
        this.user = user;
    }
}

