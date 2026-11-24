package com.claimacademy.taskmanager.config;

import com.claimacademy.taskmanager.task.Task;
import com.claimacademy.taskmanager.task.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/** Seeds a few tasks so the React app has something to show on first load. */
@Component
public class DataLoader implements CommandLineRunner {

    private final TaskRepository taskRepository;

    public DataLoader(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public void run(String... args) {
        if (taskRepository.count() > 0) {
            return;
        }
        LocalDate today = LocalDate.now();
        Task done = new Task("Set up Spring Boot project", "Generated with start.spring.io", today.minusDays(3));
        done.setCompleted(true);
        taskRepository.saveAll(List.of(
                done,
                new Task("Wire React to the API", "Axios + loading and error states", today.plusDays(1)),
                new Task("Configure CORS", "Allow http://localhost:5173 in dev", today.plusDays(2)),
                new Task("Book AWS CLF-C02 exam", null, today.plusDays(5))));
    }
}
