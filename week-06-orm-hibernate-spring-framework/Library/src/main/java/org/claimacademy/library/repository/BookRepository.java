package org.claimacademy.library.repository;

import org.claimacademy.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthor(String author);
    boolean existsByAuthor(String author);
    List<Book> findByTitleContainingIgnoreCase(String fragment);
    List<Book> findByPublicationYearBetween(int from, int to);
    Book findByTitle(String title);
    List<Book> findByPublicationYear(int publicationYear);
    List<Book> findByAuthorContaining(String author);
    Optional<Book> findBookByTitle(String title);
}
