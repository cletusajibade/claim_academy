package com.claimacademy.taskmanager.task;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    /** Only the logged-in user's tasks. */
    List<Task> findAllByOwnerUsernameOrderByCompletedAscDueDateAsc(String username);

    /** Returns empty if the task exists but belongs to someone else → 404, not 403 (don't leak ids). */
    Optional<Task> findByIdAndOwnerUsername(Long id, String username);
}
