package com.claimacademy.taskmanager.config;

import com.claimacademy.taskmanager.task.Task;
import com.claimacademy.taskmanager.task.TaskRepository;
import com.claimacademy.taskmanager.user.AppUser;
import com.claimacademy.taskmanager.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

/** Seeds a demo user (demo / password123) with a few tasks. */
@Component
@Profile("!prod") // never seed a demo account in production
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final PasswordEncoder passwordEncoder;

    public DataLoader(UserRepository userRepository, TaskRepository taskRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        if (userRepository.count() > 0) {
            return;
        }
        AppUser demo = userRepository.save(new AppUser("demo", passwordEncoder.encode("password123")));

        LocalDate today = LocalDate.now();
        Task done = new Task("Set up Spring Boot project", "Generated with start.spring.io", today.minusDays(3), demo);
        done.setCompleted(true);
        taskRepository.saveAll(List.of(
                done,
                new Task("Add Spring Security + JWT", "Stateless, Bearer tokens", today, demo),
                new Task("Store the token in memory", "React context, not localStorage", today.plusDays(1), demo),
                new Task("Book AWS CLF-C02 exam", null, today.plusDays(5), demo)));
    }
}
