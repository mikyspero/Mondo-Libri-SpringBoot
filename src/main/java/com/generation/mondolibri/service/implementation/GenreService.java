package com.generation.mondolibri.service.implementation;
import com.generation.mondolibri.model.entity.Genre;
import com.generation.mondolibri.repository.jpa.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GenreService extends AbstractService<Genre,Integer> {
    @Autowired
    public GenreService(GenreRepository repository) {
        super(repository);
    }
    @Transactional
    public Genre findOrCreateGenreByName(String nameToBeChecked) {
            return ((GenreRepository) repository).findByName(nameToBeChecked).orElseGet(
                    () -> this.save(new Genre(nameToBeChecked))
            );
    }
}
