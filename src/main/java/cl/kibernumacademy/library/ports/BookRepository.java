package cl.kibernumacademy.library.ports;

import java.util.Optional;

import cl.kibernumacademy.library.domain.Book;

public interface BookRepository {
    Optional<Book> findByIsbn(String isbn);
    void save(Book book); // Para actualizar stock (simulado)
}