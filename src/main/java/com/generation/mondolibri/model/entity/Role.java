package com.generation.mondolibri.model.entity;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entity class representing a role that can be assigned to users.
 * Each Role can have multiple associated users.
 */
@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;  // Name of the role

    @OneToMany(mappedBy = "role")
    private List<User> users;  // List of users associated with this role

    /**
     * Default constructor required by Hibernate.
     * Initializes an empty instance of Role.
     */
    public Role() {
    }

    /**
     * Constructor that accepts a name for the role.
     * @param nome Name of the role.
     */
    public Role(String nome) {
        this.nome = nome;
    }

    /**
     * Constructor that accepts a list of users and a name for the role.
     * @param users List of users associated with this role.
     * @param nome Name of the role.
     */
    public Role(List<User> users, String nome) {
        this.users = users;
        this.nome = nome;
    }

    /**
     * Retrieves the name of the role.
     * @return Name of the role.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Sets the name of the role.
     * @param nome Name of the role.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retrieves the list of users associated with this role.
     * @return List of users associated with this role.
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * Sets the list of users associated with this role.
     * @param users List of users associated with this role.
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
