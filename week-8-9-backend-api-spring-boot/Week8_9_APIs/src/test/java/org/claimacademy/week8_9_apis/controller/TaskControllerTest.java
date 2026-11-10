package org.claimacademy.week8_9_apis.controller;

import org.claimacademy.week8_9_apis.dto.TaskRequestDto;
import org.claimacademy.week8_9_apis.model.Task;
import org.claimacademy.week8_9_apis.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TaskControllerTest {

    private TaskController taskController;
    private TaskService taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskService();
        taskController = new TaskController(taskService);
    }

    @Test
    void createTask_withSpacesInTitle_shouldReturnCreatedWithValidLocationUri() {
        TaskRequestDto dto = new TaskRequestDto("Get groceries", "Buy milk and bread", false, LocalDate.now().plusDays(1));

        ResponseEntity<Task> response = taskController.createTask(dto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Get groceries", response.getBody().getTitle());
        Long id = response.getBody().getId();
        assertEquals(URI.create("/tasks/" + id), response.getHeaders().getLocation());
    }

    @Test
    void getAllTasks_shouldReturnTaskList() {
        List<Task> tasks = taskController.getAllTasks();
        assertNotNull(tasks);
        assertEquals(3, tasks.size());
    }

    @Test
    void getTaskById_shouldReturnTaskWhenFound() {
        ResponseEntity<Task> response = taskController.getTaskById(1L);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(1L, response.getBody().getId());
    }
}
