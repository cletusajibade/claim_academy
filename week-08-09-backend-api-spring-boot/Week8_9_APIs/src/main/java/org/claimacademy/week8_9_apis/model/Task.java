package org.claimacademy.week8_9_apis.model;

import java.time.LocalDate;

public class Task {
    private Long id;
    private String title;
    private String description;
    private boolean complete;
    private LocalDate dueDate;

    public Task(){}

    public Task(Long id, String title, String description, boolean complete, LocalDate dueDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.complete = complete;
        this.dueDate = dueDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", complete=" + complete +
                ", dueDate=" + dueDate +
                '}';
    }
}
