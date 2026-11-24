package org.claimacademy.wednesdaytaskmanagerapi.config;

import org.claimacademy.wednesdaytaskmanagerapi.model.Task;
import org.claimacademy.wednesdaytaskmanagerapi.repository.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * Runs once, right after startup, and inserts sample tasks.
 * Only created when app.seed-data=true (switch off with SEED_DATA=false in production).
 */
@Component
@ConditionalOnProperty(name = "app.seed-data", havingValue = "true")
public class DataSeeder implements CommandLineRunner {

    private final TaskRepository repository;

    public DataSeeder(TaskRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) {
        if (repository.count() > 0) {
            return;   // never duplicate data in a real database
        }
        repository.save(new Task("Write lecture notes", "Cover REST design", false, LocalDate.now().plusDays(3)));
        repository.save(new Task("Review pull requests", "Check open PRs", true, LocalDate.now().minusDays(1)));
        repository.save(new Task("Plan the capstone", "Draft the idea", false, LocalDate.now().minusDays(2)));
    }
}
