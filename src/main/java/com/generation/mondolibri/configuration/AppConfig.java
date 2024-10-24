package com.generation.mondolibri.configuration;

import com.generation.mondolibri.repository.jpa.*;
import com.generation.mondolibri.service.implementation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    // Service Beans
    @Bean
    public CartService cartService(CartRepository cartRepository, UserRepository userRepository, ProductRepository productRepository) {
        return new CartService(cartRepository, userRepository, productRepository);
    }

    @Bean
    public ProductService productService(ProductRepository productRepository, CartRepository cartRepository) {
        return new ProductService(productRepository, cartRepository);
    }

    @Bean
    public PurchaseService purchaseService
            (PurchaseRepository purchaseRepository,
             ProductRepository productRepository, CartRepository cartRepository) {
        return new PurchaseService(purchaseRepository,cartRepository,productRepository);
    }

    @Bean
    public UserService userService(UserRepository userRepository, CartRepository cartRepository) {
        return new UserService(userRepository, cartRepository);
    }

    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleService(roleRepository);
    }

    @Bean
    public AuthorService authorService(AuthorRepository authorRepository) {
        return new AuthorService(authorRepository);
    }

    @Bean
    public GenreService genreService(GenreRepository genreRepository) {
        return new GenreService(genreRepository);
    }
}