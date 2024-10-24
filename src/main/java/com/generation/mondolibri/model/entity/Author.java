package com.generation.mondolibri.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Represents an author entity associated with products.
 * This class is mapped to a database table using JPA annotations.
 */
@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name; // Name of the author

    @OneToMany(mappedBy = "author")
    private List<Product> products; // List of products associated with the author

    public Author() {}

    public Author(String name) {
        this.name = name;
    }

    /**
     * Retrieves the list of products associated with this author.
     *
     * @return List of products associated with the author.
     */
    public List<Product> getProducts() {
        return products;
    }

    /**
     * Sets the list of products associated with this author.
     *
     * @param products List of products to associate with the author.
     */
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    /**
     * Retrieves the name of the author.
     *
     * @return Name of the author.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the author.
     *
     * @param name Name of the author.
     */
    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
