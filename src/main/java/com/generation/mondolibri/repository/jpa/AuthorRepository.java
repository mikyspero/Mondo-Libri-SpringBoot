package com.generation.mondolibri.repository.jpa;

import com.generation.mondolibri.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    // Custom query method to find an Author by name
    Optional<Author> findByName(String name);
}
