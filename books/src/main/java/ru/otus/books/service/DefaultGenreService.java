package ru.otus.books.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.books.model.Genre;
import ru.otus.books.repository.GenreRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class DefaultGenreService implements GenreService {

    private final GenreRepository repository;

    @Override
    public Genre getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public List<Genre> getAll() {
        return repository.getAll();
    }

    @Override
    @Transactional
    public Genre create(String title) {
        return repository.create(Genre.builder()
                .title(title)
                .build());
    }

    @Override
    @Transactional
    public int update(Long id, String title) {
        return repository.update(id, title);
    }

    @Override
    @Transactional
    public int deleteById(Long id) {
        return repository.deleteById(id);
    }
}
