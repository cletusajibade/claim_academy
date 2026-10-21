package org.claimacademy.library;

import org.claimacademy.library.model.Book;
import org.claimacademy.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class StartupRunner implements CommandLineRunner {

    private final BookRepository bookRepository;

    //Constructor/Dependency injection
    public StartupRunner(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // CRUD
        IO.println("Books count:" + bookRepository.count());
        if (bookRepository.count() == 0) {
            bookRepository.save(new Book("Programming Java", "John Doe", 1956, "Technology", new BigDecimal("19.99"), 15));
            bookRepository.save(new Book("Java for Beginners", "Jane Smith", 2022, "Technology", new BigDecimal("24.99"), 10));
            bookRepository.save(new Book("Advanced Java", "Alice Johnson", 2018, "Technology", new BigDecimal("39.99"), 5));
            bookRepository.save(new Book("Python Programming", "Mark Brown", 2018, "Programming", new BigDecimal("29.99"), 8));
        }

        IO.println("Books added to the database:  " + bookRepository.count());
        bookRepository.findAll().forEach(book -> IO.println(book));

        IO.println("Books by Alice Johnson: ");
        bookRepository.findByAuthor("Alice Johnson").forEach(book -> IO.println(book));

        IO.println("findByAuthorContaining: ");
        bookRepository.findByAuthorContaining("Johnson").forEach(book -> IO.println(book));

        IO.println("findByTitle:");
        IO.println(bookRepository.findByTitle("Python Programming"));
        IO.println("findByPublicationYear:");
        IO.println(bookRepository.findByPublicationYear(2018));

        bookRepository.deleteById(3);
    }
}
