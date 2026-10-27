package org.claimacademy.library.exception;

public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(int id) {
        super("A book with id " + id + " already exists");
    }
}