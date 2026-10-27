package org.claimacademy.library.exception;

public class BookInUseException extends RuntimeException {
    public BookInUseException(int id, Throwable cause) {
        super("Book " + id + " cannot be deleted because it has loan records", cause);
    }
}
