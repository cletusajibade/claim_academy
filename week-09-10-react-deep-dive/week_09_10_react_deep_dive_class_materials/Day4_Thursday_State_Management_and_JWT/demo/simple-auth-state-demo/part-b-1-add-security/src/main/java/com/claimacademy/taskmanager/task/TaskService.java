package com.claimacademy.taskmanager.task;

import com.claimacademy.taskmanager.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> findAll() {
        return taskRepository.findAllByOrderByCompletedAscDueDateAsc()
                .stream()
                .map(TaskResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public TaskResponse findById(Long id) {
        return TaskResponse.from(getTask(id));
    }

    public TaskResponse create(TaskRequest request) {
        Task task = new Task(request.title().trim(), request.description(), request.dueDate());
        return TaskResponse.from(taskRepository.save(task));
    }

    public TaskResponse update(Long id, TaskRequest request) {
        Task task = getTask(id);
        task.setTitle(request.title().trim());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        return TaskResponse.from(task); // dirty checking saves on commit
    }

    public TaskResponse patchCompleted(Long id, TaskPatchRequest request) {
        Task task = getTask(id);
        task.setCompleted(request.completed());
        return TaskResponse.from(task);
    }

    public void delete(Long id) {
        taskRepository.delete(getTask(id));
    }

    private Task getTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task " + id + " not found"));
    }
}
