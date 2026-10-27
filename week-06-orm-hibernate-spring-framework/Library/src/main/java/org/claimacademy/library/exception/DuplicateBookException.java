package org.claimacademy.library.exception;

public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(String isbn) {
        super("A book with ISBN " + isbn + " already exists");
    }
}