package com.example.taskmanager.controller;

import com.example.taskmanager.dto.TaskRequest;
import com.example.taskmanager.model.Task;
import com.example.taskmanager.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * Full CRUD:
 *   POST   /tasks       201 + task + Location   (400 invalid)
 *   GET    /tasks       200 + array
 *   GET    /tasks/{id}  200 + task              (404)
 *   PUT    /tasks/{id}  200 + task              (400, 404)
 *   DELETE /tasks/{id}  204                     (404)
 */
@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    // No more Optional here: the service throws TaskNotFoundException and the handler answers 404.
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    // @RequestBody: build a TaskRequest from the JSON body.  @Valid: check the rules BEFORE this method runs.
    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest request) {
        Task created = taskService.create(request);
        return ResponseEntity
                .created(URI.create("/tasks/" + created.getId()))   // 201 + Location header
                .body(created);
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id,
                           @Valid @RequestBody TaskRequest request) {
        return taskService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();                  // 204, no body
    }
}
