package com.generation.mondolibri.repository.jpa;

import com.generation.mondolibri.model.entity.Cart;
import com.generation.mondolibri.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    // Find a cart by the associated user
    Optional<Cart> findByUser(User user);
}
