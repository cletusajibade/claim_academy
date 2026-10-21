package org.claimacademy.library.repository;

import org.claimacademy.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthor(String author);
    Book findByTitle(String title);
    List<Book> findByPublicationYear(int publicationYear);
    List<Book> findByAuthorContaining(String author);
}
