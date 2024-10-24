package com.generation.mondolibri.repository.jpa;

import com.generation.mondolibri.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Custom query to find a Product by name
    Optional<Product> findByName(@Param("name") String name);

    // Custom query to search products by name (case-insensitive)
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Product> find(@Param("name") String name);

    // Custom query to search products by a price range
    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> find(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);

    // Custom query to search products by name and price range
    @Query("SELECT p FROM Product p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :name, '%')) AND p.price BETWEEN :minPrice AND :maxPrice")
    List<Product> find(@Param("name") String name, @Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
}
