package org.claimacademy.wednesdaytaskmanagerapi.service;

import org.claimacademy.wednesdaytaskmanagerapi.dto.TaskRequest;
import org.claimacademy.wednesdaytaskmanagerapi.exception.TaskNotFoundException;
import org.claimacademy.wednesdaytaskmanagerapi.model.Task;
import org.claimacademy.wednesdaytaskmanagerapi.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Only this class changed when we moved from the Map to a database.
 * The controller, DTO, validation and error handler did not notice. That is what layers are for.
 */
@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> findAll() {
        return repository.findAll();
    }

    public Task findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TaskNotFoundException(id));
    }

    public Task create(TaskRequest request) {
        Task task = new Task(request.title(), request.description(), request.complete(), request.dueDate());
        return repository.save(task);      // insert: the database assigns the id
    }

    public Task update(Long id, TaskRequest request) {
        Task task = findById(id);
        task.setTitle(request.title());
        task.setDescription(request.description());
        task.setComplete(request.complete());
        task.setDueDate(request.dueDate());
        return repository.save(task);      // update: the task already has an id
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new TaskNotFoundException(id);
        }
        repository.deleteById(id);
    }
}
