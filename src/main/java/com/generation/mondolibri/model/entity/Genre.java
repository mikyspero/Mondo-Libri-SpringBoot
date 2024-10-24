package com.generation.mondolibri.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity class representing a genre of products.
 * Each Genre can have multiple associated products.
 */
@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;  // Name of the genre

    @OneToMany(mappedBy = "genre")  // One-to-many relationship with Product, where 'genre' in Product owns the relationship
    private List<Product> products;  // List of products associated with the genre

    /**
     * Default constructor required by Hibernate.
     * Initializes an empty instance of Genre.
     */
    public Genre() {
    }

    /**
     * Constructor that initializes Genre with a list of products and a name.
     * @param products The list of products associated with this genre.
     * @param name The name of the genre.
     */
    public Genre(List<Product> products, String name) {
        this.products = products;
        this.name = name;
    }

    public Genre(String genreString) {
        this.name = genreString;
    }

    /**
     * Retrieves the list of products associated with this genre.
     * @return List of products associated with the genre.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets the list of products associated with this genre.
     * @param products List of products to set for the genre.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Retrieves the name of the genre.
     * @return Name of the genre.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the genre.
     * @param name Name to set for the genre.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
