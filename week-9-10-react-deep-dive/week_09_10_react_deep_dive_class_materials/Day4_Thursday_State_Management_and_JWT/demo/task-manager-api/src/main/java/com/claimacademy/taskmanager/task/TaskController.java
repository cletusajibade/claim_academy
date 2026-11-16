package com.claimacademy.taskmanager.task;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Every endpoint here requires a valid JWT (see SecurityConfig).
 * Spring injects the authenticated user as a {@link Principal}; principal.getName() is the username.
 */
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public List<TaskResponse> getAll(Principal principal) {
        return taskService.findAll(principal.getName());
    }

    @GetMapping("/{id}")
    public TaskResponse getOne(@PathVariable Long id, Principal principal) {
        return taskService.findById(id, principal.getName());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponse create(@Valid @RequestBody TaskRequest request, Principal principal) {
        return taskService.create(request, principal.getName());
    }

    @PutMapping("/{id}")
    public TaskResponse update(@PathVariable Long id, @Valid @RequestBody TaskRequest request,
                               Principal principal) {
        return taskService.update(id, request, principal.getName());
    }

    /** "Mark complete" button → PATCH /api/tasks/5  { "completed": true } */
    @PatchMapping("/{id}")
    public TaskResponse patch(@PathVariable Long id, @Valid @RequestBody TaskPatchRequest request,
                              Principal principal) {
        return taskService.patchCompleted(id, request, principal.getName());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id, Principal principal) {
        taskService.delete(id, principal.getName());
    }
}
