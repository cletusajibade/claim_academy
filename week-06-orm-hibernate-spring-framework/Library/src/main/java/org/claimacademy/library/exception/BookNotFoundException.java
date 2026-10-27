package org.claimacademy.library.exception;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(int id) {
        super("No book with id " + id);
    }
}