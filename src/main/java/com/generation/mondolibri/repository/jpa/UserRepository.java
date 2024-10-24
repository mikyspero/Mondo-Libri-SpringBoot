package com.generation.mondolibri.repository.jpa;

import com.generation.mondolibri.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // This method is already provided by JpaRepository, no need to declare
    // User save(User user);

    // Custom query methods for finding User by email or username
    Optional<User> findByEmail(String email);
    Optional<User> findByUsername(String username);
}
