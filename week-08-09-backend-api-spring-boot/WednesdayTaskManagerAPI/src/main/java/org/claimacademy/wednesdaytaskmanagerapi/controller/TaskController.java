package org.claimacademy.wednesdaytaskmanagerapi.controller;

import org.claimacademy.wednesdaytaskmanagerapi.dto.TaskRequest;
import org.claimacademy.wednesdaytaskmanagerapi.model.Task;
import org.claimacademy.wednesdaytaskmanagerapi.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@RestController
@RequestMapping("/tasks")
@Tag(name = "Tasks", description = "Create, read, update and delete tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    @Operation(summary = "List all tasks")
    @ApiResponse(responseCode = "200", description = "Every task (possibly empty)")
    public List<Task> getAllTasks() {
        return taskService.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get one task by id")
    @ApiResponse(responseCode = "200", description = "The task")
    @ApiResponse(responseCode = "404", description = "No task with that id")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.findById(id);
    }

    @PostMapping
    @Operation(summary = "Create a task")
    @ApiResponse(responseCode = "201", description = "Task created; the Location header holds its URL")
    @ApiResponse(responseCode = "400", description = "Validation failed (for example a blank title)")
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest request) {
        Task created = taskService.create(request);
        return ResponseEntity
                .created(URI.create("/tasks/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Replace a task", description = "Send the complete task. Fields you leave out are cleared.")
    @ApiResponse(responseCode = "200", description = "The updated task")
    @ApiResponse(responseCode = "400", description = "Validation failed")
    @ApiResponse(responseCode = "404", description = "No task with that id")
    public Task updateTask(@PathVariable Long id, @Valid @RequestBody TaskRequest request) {
        return taskService.update(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task")
    @ApiResponse(responseCode = "204", description = "Task deleted; no body")
    @ApiResponse(responseCode = "404", description = "No task with that id")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
