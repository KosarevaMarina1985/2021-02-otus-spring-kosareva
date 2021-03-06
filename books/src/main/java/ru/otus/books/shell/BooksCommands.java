package ru.otus.books.shell;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.otus.books.model.Book;
import ru.otus.books.service.BookService;

import java.util.List;

@ShellComponent
@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class BooksCommands {
    private final BookService service;

    @ShellMethod(value = "Get all books", key = "getBooks")
    public List<Book> getBooks() {
        return service.getAll();
    }

    @ShellMethod(value = "Get book by id", key = "getBookById")
    public Book getBookById(Long id) {
        return service.getById(id);
    }

    @ShellMethod(value = "Create book by title, genreId and authorId", key = "createBook")
    public Book createBook(String title, Long genreId, Long authorId) {
        return service.create(title, genreId, authorId);
    }

    @ShellMethod(value = "Update book's title by id", key = "updateBook")
    public Book updateBook(Long id, String title) {
        return service.update(id, title);
    }

    @ShellMethod(value = "Delete book by id", key = "deleteBook")
    public String deleteBook(Long id) {
        service.deleteById(id);
        return "successfully deleted";
    }
}
