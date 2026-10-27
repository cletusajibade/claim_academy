package org.claimacademy.library.data;

import org.claimacademy.library.exception.BookInUseException;
import org.claimacademy.library.exception.BookNotFoundException;
import org.claimacademy.library.exception.DuplicateBookException;
import org.claimacademy.library.model.Book;
import org.claimacademy.library.repository.BookRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookCatalog {
    private final BookRepository bookRepository;

    public BookCatalog(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll(Sort.by("title").ascending());
    }

    public Book addBook(Book book) {
        if (bookRepository.existsByAuthor(book.getAuthor())) {
            throw new DuplicateBookException(book.getId());
        }
        return bookRepository.save(book); // return the result to the caller after saving the book in the database.
    }

    public void deleteBook(int bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new BookNotFoundException(bookId);
        }

        try {
            bookRepository.deleteById(bookId);
        } catch (DataIntegrityViolationException ex) {
            throw new BookInUseException(bookId, ex);
        }
    }
}
