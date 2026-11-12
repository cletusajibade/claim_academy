package com.example.taskmanager.service;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.exception.TaskNotFoundException;
import com.example.taskmanager.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Business rules. Knows nothing about HTTP: it THROWS when something is wrong
 * and lets GlobalExceptionHandler choose the status code.
 */
@Service
public class TaskService {

    private final Map<Long, Task> tasks = new ConcurrentSkipListMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public TaskService() {
        add("Write lecture notes", "Cover REST design", false, LocalDate.now().plusDays(3));
        add("Review pull requests", "Check open PRs", true, LocalDate.now().minusDays(1));
        add("Plan the capstone", "Draft the idea", false, LocalDate.now().minusDays(2));
    }

    private Task add(String title, String description, boolean complete, LocalDate dueDate) {
        long id = nextId.getAndIncrement();            // the SERVER decides the id
        Task task = new Task(id, title, description, complete, dueDate);
        tasks.put(id, task);
        return task;
    }

    public List<Task> findAll() {
        return new ArrayList<>(tasks.values());
    }

    public Task findById(Long id) {
        Task task = tasks.get(id);
        if (task == null) {
            throw new TaskNotFoundException(id);
        }
        return task;
    }

    public Task create(TaskRequest request) {
        return add(request.title(), request.description(), request.complete(), request.dueDate());
    }

    // PUT means REPLACE: every field is overwritten. A field the client leaves out becomes null/false.
    public Task update(Long id, TaskRequest request) {
        Task task = findById(id);
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setComplete(request.complete());
        task.setDueDate(request.dueDate());
        return task;
    }

    public void delete(Long id) {
        if (tasks.remove(id) == null) {
            throw new TaskNotFoundException(id);
        }
    }
}
