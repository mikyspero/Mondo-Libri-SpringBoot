package com.generation.mondolibri.repository.jpa;

import com.generation.mondolibri.model.entity.Product;
import com.generation.mondolibri.model.entity.Purchase;
import com.generation.mondolibri.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA Repository for Purchase entities.
 */
@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {

    /**
     * Finds all purchases made by a specific user.
     * Returns empty list if no purchases found.
     */
    Optional<List<Purchase>> findByUser(User user);

    /**
     * Finds all purchases involving a specific product.
     * Returns empty list if no purchases found.
     */
    Optional<List<Purchase>> findByProduct(Product product);

    /**
     * Finds all purchases made within a date range.
     * Returns empty list if no purchases found.
     */
    Optional<List<Purchase>> findByPurchaseDateBetween(Date startDate, Date endDate);

    /**
     * Finds the most recent purchases.
     * Returns empty list if no purchases found.
     */
    @Query("SELECT p FROM Purchase p ORDER BY p.purchaseDate DESC")
    Optional<List<Purchase>> findMostRecentPurchases(@Param("limit") int limit);

    /**
     * Finds user's most recent purchase.
     */
    Optional<Purchase> findFirstByUserOrderByPurchaseDateDesc(User user);

    /**
     * Finds product's most recent purchase.
     */
    Optional<Purchase> findFirstByProductOrderByPurchaseDateDesc(Product product);

    /**
     * Finds latest purchase of a product by a user.
     */
    @Query("SELECT p FROM Purchase p WHERE p.user = :user AND p.product = :product ORDER BY p.purchaseDate DESC")
    Optional<Purchase> findLatestUserProductPurchase(@Param("user") User user, @Param("product") Product product);
}