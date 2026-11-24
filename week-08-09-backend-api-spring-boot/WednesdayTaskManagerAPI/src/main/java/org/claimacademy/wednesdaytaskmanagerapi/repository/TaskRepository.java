package org.claimacademy.wednesdaytaskmanagerapi.repository;

import org.claimacademy.wednesdaytaskmanagerapi.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * That is the whole file. Spring writes the implementation at startup:
 * findAll(), findById(id), save(task), deleteById(id), existsById(id), count() ...
 */
public interface TaskRepository extends JpaRepository<Task, Long> {
}
