package com.generation.mondolibri.service.implementation;

import com.generation.mondolibri.model.entity.Author;

import com.generation.mondolibri.repository.jpa.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorService extends AbstractService<Author,Integer> {
    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        super(authorRepository);
    }

    @Transactional
    public Author findOrCreateAuthorByName(String nameToBeChecked) {
        return ((AuthorRepository) repository).findByName(nameToBeChecked)
                .orElseGet(() -> this.save(new Author(nameToBeChecked)));
    }

}