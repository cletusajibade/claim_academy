package org.claimacademy.library;

import org.claimacademy.library.model.Book;
import org.claimacademy.library.model.Loan;
import org.claimacademy.library.model.Member;
import org.claimacademy.library.repository.BookRepository;
import org.claimacademy.library.repository.LoanRepository;
import org.claimacademy.library.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;
import java.util.List;

@Component
public class StartupRunner implements CommandLineRunner {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final LoanRepository loanRepository;
    private final TransactionTemplate transactionTemplate;

    //Constructor/Dependency injection
    public StartupRunner(BookRepository bookRepository, MemberRepository memberRepository, LoanRepository loanRepository, TransactionTemplate transactionTemplate) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
        this.transactionTemplate = transactionTemplate;
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

        seedLoans();
    }

    private void seedLoans() {
        List<Book> books = bookRepository.findAll();

        Member starlet = new Member("Starlet", "starlet@gmail.com");
        Member reggie = new Member("Reggie", "reggie@gmail.com");

        starlet.addLoan(new Loan(books.get(0), starlet)); // index 0 => book_id 1 in the table
        starlet.addLoan(new Loan(books.get(1), starlet)); // index 1 => book_id 2 in the table
        reggie.addLoan(new Loan(books.get(0), reggie));

        // Cascading from member persists the loans
        memberRepository.saveAll(List.of(starlet, reggie)); // saves multiple entity

        //memberRepository.save(starlet); //saves one entity
        //memberRepository.save(reggie); //saves one entity
    }
}
