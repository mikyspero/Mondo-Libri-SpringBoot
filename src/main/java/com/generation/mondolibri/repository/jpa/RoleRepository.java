package com.generation.mondolibri.repository.jpa;

import com.generation.mondolibri.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // You can define additional query methods here if needed
}