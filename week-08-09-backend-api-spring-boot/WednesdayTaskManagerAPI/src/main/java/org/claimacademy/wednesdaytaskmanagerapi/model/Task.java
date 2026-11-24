package org.claimacademy.wednesdaytaskmanagerapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

/**
 * Each Task object is one row in the "tasks" table.
 * Imports come from jakarta.persistence (Spring Boot 3), never javax.persistence.
 */
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // the database assigns the id
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(length = 1000)
    private String description;

    @Column(nullable = false)
    private boolean complete;

    private LocalDate dueDate;                             // column name: due_date

    protected Task() {
        // JPA needs a no-argument constructor
    }

    // No id parameter: the DATABASE creates the id when we save.
    public Task(String title, String description, boolean complete, LocalDate dueDate) {
        this.title = title;
        this.description = description;
        this.complete = complete;
        this.dueDate = dueDate;
    }

    public Long getId() { return id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public boolean isComplete() { return complete; }
    public void setComplete(boolean complete) { this.complete = complete; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
}
