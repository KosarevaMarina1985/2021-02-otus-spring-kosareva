package ru.otus.books.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.books.model.Genre;
import ru.otus.books.service.GenreService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class GenresCommands {

    private final GenreService service;

    @ShellMethod(value = "Get all genres", key = "getGenres")
    public List<Genre> getGenres() {
        return service.getAll();
    }

    @ShellMethod(value = "Get genre by id", key = "getGenreById")
    public Genre getGenreById(Long id) {
        return service.getById(id);
    }

    @ShellMethod(value = "Create genre by title", key = "createGenre")
    public Genre createGenre(String title) {
        return service.create(title);
    }

    @ShellMethod(value = "Update genre's title by id", key = "updateGenre")
    public String updateGenre(Long id, String title) {
        return service.update(id, title) == 1
                ? "successfully updated"
                : "not updated: possible the genre with this id does not exist";
    }

    @ShellMethod(value = "Delete genre by id", key = "deleteGenre")
    public String deleteGenre(Long id) {
        return service.deleteById(id) == 1
                ? "successfully deleted"
                : "not deleted: possible the genre with this id does not exist";
    }
}
