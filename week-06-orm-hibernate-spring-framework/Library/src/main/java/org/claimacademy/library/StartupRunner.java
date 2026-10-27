package org.claimacademy.library;

import org.claimacademy.library.data.BookCatalog;
import org.claimacademy.library.model.Book;
import org.claimacademy.library.model.Loan;
import org.claimacademy.library.model.Member;
import org.claimacademy.library.repository.BookRepository;
import org.claimacademy.library.repository.LoanRepository;
import org.claimacademy.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Value;
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
    // A transaction is a group of database operations that are executed as a single unit of work.
    // It can succeed or fail as a whole, ensuring data consistency and integrity.
    // It makes sure that ACID properties are maintained.
    // 1. Atomicity: A transaction is an atomic unit of work. Either all the operations in the transaction are executed successfully, or none of them are.
    // 2. Consistency: A transaction ensures that the database is in a consistent state before and after its execution. It maintains data integrity and prevents data corruption.
    // 3. Isolation: Transactions are isolated from each other, ensuring that concurrent transactions do not interfere with each other. It prevents dirty reads, non-repeatable reads, and phantom reads.
    // 4. Durability: Once a transaction is committed, its changes are permanently stored in the database. It ensures that committed transactions are not lost even in the event of system failures.
    private final TransactionTemplate transactionTemplate;

    private final BookCatalog bookCatalog;

    // It fetches whatever 'demo.stage' property is
    private final String demoStage;

    private static final String EMAIL = "zigzigler@email.com";
    private static final String BOOK_TITLE = "Elements of Programming in Java";
    private static final String AUTHOR = "Zig Zigler";
    private static final int PUB_YEAR = 1967;
    private static final String GENRE = "Technology";
    private static final BigDecimal PRICE = new BigDecimal("28.99");
    private static final int COPIES = 10;

    //Constructor/Dependency injection
    public StartupRunner(BookRepository bookRepository,
                         MemberRepository memberRepository,
                         LoanRepository loanRepository,
                         TransactionTemplate transactionTemplate,
                         @Value("${demo.stage}") String demoStage,
                         BookCatalog catalog) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.loanRepository = loanRepository;
        this.transactionTemplate = transactionTemplate;
        this.demoStage = demoStage;
        this.bookCatalog = catalog;

    }

    @Override
    public void run(String... args) throws Exception {
        if (demoStage.equals("none")) {
            return;
        }

        //seedLoans();
        runOperations();
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

    public void runOperations() {
        switch (demoStage) {
            case "create" -> createOperation();
            case "read" -> readOperation();
            case "update" -> updateOperation();
            case "delete" -> deleteOperation();
            case "none" -> {
            }
            default -> IO.println("Unknown demo.stage '" + demoStage + "'(use none, create, read, update or delete)");
        }
    }

    private void createOperation() {
        //CRUD
        // Data Transfer Object (DTO) - this is just a plain data values carrier
        Book addedBook = bookCatalog.addBook(new Book("In the beginning", "Jesus", 2000, "Religion", new BigDecimal("50.99"), 3));
        IO.println("Added book with id " + addedBook.getId());

//        if (memberRepository.findByEmail(EMAIL).isPresent()) {
//            IO.println("Member already in the database; run delete first");
//            return;
//        }
//
//        transactionTemplate.executeWithoutResult(status -> {
//            Member member = new Member("James Gosling", EMAIL);
//            Book book = new Book(BOOK_TITLE, AUTHOR, PUB_YEAR, GENRE, PRICE, COPIES);
//            bookRepository.save(book);
//
//            member.addLoan(new Loan(book, member));
//            memberRepository.save(member);
//        });
//
//        if (!demoStage.equals("none")) {
//            IO.println("Table rows after '" + demoStage + "' stage");
//            printOperations();
//        }
    }

    private void readOperation() {
        // The two lines below are equivalent:
        // bookCatalog.findAllBooks().forEach(IO::println);
        // bookCatalog.findAllBooks().forEach(book->IO.println(book));

        // These two lines are doing what either of the above lines does.
        List<Book> bookList = bookCatalog.findAllBooks();
        bookList.forEach(book -> IO.println(book));
    }

    private void updateOperation() {

    }

    private void deleteOperation() {

    }

    private void printOperations() {
        transactionTemplate.executeWithoutResult(status -> {
            memberRepository.findAll().forEach(m -> {
                IO.println(m.getName() + " has " + m.getLoans().size() + " loan(s)");
                m.getLoans().forEach(l -> IO.println("   borrowed " + l.getBook().getTitle()));
            });

            bookRepository.findAll().forEach(b -> {
                if (!b.getLoans().isEmpty()) {
                    IO.println(b.getTitle() + " borrowed by: " + b.getLoans().stream().map(l -> l.getMember().getName()).toList());
                }
            });
        });
    }
}
