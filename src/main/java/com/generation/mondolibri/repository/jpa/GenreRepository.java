package com.generation.mondolibri.repository.jpa;

import com.generation.mondolibri.model.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
    // Custom query to find a Genre by name, wrapped in Optional
    Optional<Genre> findByName(String name);
}
