package com.claimacademy.taskmanager.task;

import com.claimacademy.taskmanager.exception.ResourceNotFoundException;
import com.claimacademy.taskmanager.user.AppUser;
import com.claimacademy.taskmanager.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> findAll(String username) {
        return taskRepository.findAllByOwnerUsernameOrderByCompletedAscDueDateAsc(username)
                .stream()
                .map(TaskResponse::from)
                .toList();
    }

    @Transactional(readOnly = true)
    public TaskResponse findById(Long id, String username) {
        return TaskResponse.from(getOwnedTask(id, username));
    }

    public TaskResponse create(TaskRequest request, String username) {
        AppUser owner = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User " + username + " not found"));
        Task task = new Task(request.title().trim(), request.description(), request.dueDate(), owner);
        return TaskResponse.from(taskRepository.save(task));
    }

    public TaskResponse update(Long id, TaskRequest request, String username) {
        Task task = getOwnedTask(id, username);
        task.setTitle(request.title().trim());
        task.setDescription(request.description());
        task.setDueDate(request.dueDate());
        return TaskResponse.from(task);
    }

    public TaskResponse patchCompleted(Long id, TaskPatchRequest request, String username) {
        Task task = getOwnedTask(id, username);
        task.setCompleted(request.completed());
        return TaskResponse.from(task);
    }

    public void delete(Long id, String username) {
        taskRepository.delete(getOwnedTask(id, username));
    }

    private Task getOwnedTask(Long id, String username) {
        return taskRepository.findByIdAndOwnerUsername(id, username)
                .orElseThrow(() -> new ResourceNotFoundException("Task " + id + " not found"));
    }
}
