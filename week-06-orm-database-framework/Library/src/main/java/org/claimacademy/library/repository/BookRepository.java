package org.claimacademy.library.repository;

import org.claimacademy.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findByAuthor(String author);
    Book findByTitle(String title);
    List<Book> findByPublicationYear(int publicationYear);
    List<Book> findByAuthorContaining(String author);
    //Using the JPQL - Jakarta Persistence Query Language
    // Like '%program%'
    @Query("select b from Book b where lower(b.title) like lower(concat('%', :text, '%'))")
    List<Book> search(@Param("text") String text);
}
