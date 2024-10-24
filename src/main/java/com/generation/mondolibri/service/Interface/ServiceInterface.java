package com.generation.mondolibri.service.Interface;

import java.util.List;
import java.util.Optional;

public interface ServiceInterface<T, ID> {

    T save(T entity);

    Optional<T> findById(ID id);

    List<T> findAll();

    void deleteById(ID id);

    boolean existsById(ID id);
}
