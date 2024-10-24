package com.generation.mondolibri.service.implementation;

import com.generation.mondolibri.service.Interface.ServiceInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * A generic service implementation using JpaRepository.
 *
 * @param <T>  Entity type.
 * @param <ID> Entity ID type.
 */
public abstract class AbstractService<T, ID> implements ServiceInterface<T, ID> {

    // Repository will be injected by subclasses.
    protected final JpaRepository<T, ID> repository;

    protected AbstractService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public T save(T entity) {
        return repository.save(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }
}

