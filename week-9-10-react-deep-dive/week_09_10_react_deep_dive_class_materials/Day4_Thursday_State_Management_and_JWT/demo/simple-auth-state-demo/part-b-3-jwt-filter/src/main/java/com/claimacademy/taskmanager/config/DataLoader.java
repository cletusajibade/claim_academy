package com.claimacademy.taskmanager.config;

import com.claimacademy.taskmanager.task.Task;
import com.claimacademy.taskmanager.task.TaskRepository;
import com.claimacademy.taskmanager.user.AppUser;
import com.claimacademy.taskmanager.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/** Seeds a demo user (demo / password123) and a few tasks. */
@Component
public class DataLoader implements CommandLineRunner {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(TaskRepository taskRepository, UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() == 0) {
            // Store the BCrypt HASH of the password, never the password itself
            userRepository.save(new AppUser("demo", passwordEncoder.encode("password123")));
        }
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
