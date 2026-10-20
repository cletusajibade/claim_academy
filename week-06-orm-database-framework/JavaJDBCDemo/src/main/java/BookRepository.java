import java.sql.*;

//JDBC - Java Database Connectivity
//Demonstrating JDBC in java
public class BookRepository {
    // What is the database address?
    // local host IP: 127.0.0.1
    // This tells our java program how to locate the postgres database on our machine
    private static final String URL =
            "jdbc:postgresql://localhost:5440/library_db";
    private static final String USERNAME =
            "postgres";
    private static final String PASSWORD =
            ""; // Don't do this in real applications.

    // Every method has a return type
    public Book findById(long book_id) throws SQLException {
        String sql = "SELECT book_id, " +
                "title, " +
                "author, " +
                "publication_year, " +
                "genre, " +
                "price, " +
                "copies " +
                "FROM books WHERE book_id=?";

        // Create the connection to the database server
        try (
                //Using try-with-resources, so no need to manually close the resources in a 'finally' block
                Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                PreparedStatement ps = connection.prepareStatement(sql)
        ) {


            ps.setLong(1, book_id);

            ResultSet resultSet = ps.executeQuery();

            // Set the column values on the book model
            if (resultSet.next()) {

                Book book = new Book(); // Give me an instance of Book
                book.setBookId(resultSet.getLong("book_id"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublicationYear(resultSet.getInt("publication_year"));
                book.setGenre(resultSet.getString("genre"));
                book.setPrice(resultSet.getDouble("price"));
                book.setCopies(resultSet.getInt("copies"));

                return book;
            }

        }

        return null;
    }

    public Book findByAuthor(String authhor){
        return null;
    }

    static void main(String[] args) {
        BookRepository repository = new BookRepository();

        try {
            Book book = repository.findById(10);
            if (book != null) {
                IO.println(book);
            } else {
                IO.println("Book not found");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
