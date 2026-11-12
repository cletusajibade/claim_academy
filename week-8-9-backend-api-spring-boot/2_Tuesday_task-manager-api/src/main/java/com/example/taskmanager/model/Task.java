package com.example.taskmanager.model;

import java.time.LocalDate;

/**
 * The resource our API moves around.
 * Jackson reads the GETTERS to write JSON and uses the no-argument constructor + SETTERS to read JSON.
 */
public class Task {

    private Long id;
    private String title;
    private String description;
    private boolean complete;      // name booleans like adjectives: JSON key is "complete"
    private LocalDate dueDate;     // JSON: "2026-11-20"

    public Task() {
    }

    public Task(Long id, String title, String description, boolean complete, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.complete = complete;
        this.dueDate = dueDate;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isComplete() { return complete; }   // boolean getter starts with "is"
    public void setComplete(boolean complete) { this.complete = complete; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}
