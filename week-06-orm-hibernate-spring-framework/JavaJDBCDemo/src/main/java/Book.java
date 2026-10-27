public class Book {
    private long bookId; // camel casing
    private String title;
    private String author;
    private int publicationYear;
    private String genre;
    private double price;
    private int copies;

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "Book = {\n" +
                "\tbookId = " + bookId + "\n" +
                "\ttitle = '" + title + '\'' +"\n" +
                "\tauthor = '" + author + '\'' +"\n" +
                "\tpublicationYear = " + publicationYear +"\n" +
                "\tgenre = '" + genre + '\'' +"\n" +
                "\tprice = " + price +"\n" +
                "\tcopies = " + copies +"\n" +
                '}';
    }
}
