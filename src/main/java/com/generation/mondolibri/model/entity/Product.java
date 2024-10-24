package com.generation.mondolibri.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity class representing a product.
 * Each Product belongs to a specific Genre and Author,
 * can be associated with multiple Carts and Purchases.
 */
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;  // Name of the product
    private Double price;  // Price of the product
    private int quantity;  // Quantity of the product
    private String description;  // Description of the product
    private String language;  // Language of the product
    private String image;  // URL of the product image

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;  // Author of the product

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;  // Genre of the product

    @ManyToMany(mappedBy = "products")
    private List<Cart> carts;  // Carts containing this product

    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases;  // Purchases of this product

    /**
     * Default constructor required by Hibernate.
     * Initializes an empty instance of Product.
     */
    public Product() {
    }

    /**
     * Constructor that accepts all fields including image URL.
     * @param purchases List of purchases associated with the product.
     * @param carts List of carts containing the product.
     * @param genre Genre of the product.
     * @param author Author of the product.
     * @param language Language of the product.
     * @param image URL of the product image.
     * @param description Description of the product.
     * @param quantity Quantity of the product.
     * @param price Price of the product.
     * @param name Name of the product.
     */
    public Product(List<Purchase> purchases, List<Cart> carts, Genre genre, Author author, String language, String image, String description, int quantity, double price, String name) {
        this.purchases = purchases;
        this.carts = carts;
        this.genre = genre;
        this.author = author;
        this.language = language;
        this.image = image;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.name = name;
    }

    /**
     * Constructor that accepts only the main fields of the product.
     * @param name Name of the product.
     * @param price Price of the product.
     * @param quantity Quantity of the product.
     * @param description Description of the product.
     * @param language Language of the product.
     */
    public Product(String name, Double price, int quantity, String description, String language, String image) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.language = language;
        this.image = image;
    }

    public Product(String name, Author author, Genre genre, Double price, int quantity, String description, String language, String image) {
        this.name = name;
        this.author = author;
        this.genre = genre;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.language = language;
        this.image = image;
    }

    // Getters and setters for all fields including the image URL

    public Integer getId() {
        return id;
    }

    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", author=" + author +
                ", price=" + price +
                '}';
    }


}
