package org.claimacademy.wednesdaytaskmanagerapi.exception;

/** Thrown by the service. It says WHAT went wrong; GlobalExceptionHandler picks the status code (404). */
public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task with id " + id + " was not found");
    }
}
