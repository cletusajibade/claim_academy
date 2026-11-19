package org.claimacademy.week8_9_apis.controller;

import jakarta.validation.Valid;
import org.claimacademy.week8_9_apis.dto.TaskRequestDto;
import org.claimacademy.week8_9_apis.model.Task;
import org.claimacademy.week8_9_apis.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

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

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        return taskService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequestDto requestDto) {
        Task createdTask = taskService.create(requestDto);

        return ResponseEntity
                .created(URI.create("/tasks/" + createdTask.getId()))
                .body(createdTask);
    }
}
