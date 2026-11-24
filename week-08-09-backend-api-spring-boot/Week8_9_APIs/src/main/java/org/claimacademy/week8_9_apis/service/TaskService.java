package org.claimacademy.week8_9_apis.service;

import org.claimacademy.week8_9_apis.dto.TaskRequestDto;
import org.claimacademy.week8_9_apis.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TaskService {
    private final Map<Long, Task> tasks= new ConcurrentSkipListMap<>();
    private final AtomicLong nextId = new AtomicLong(1);

    public TaskService(){
        add("Write lecture notes","Teach APIs",false,LocalDate.now().plusDays(3));
        add("Get groceries","Get eggs, and mangoes",true,LocalDate.now().minusDays(2));
        add("Plan a capstone","Draft ideas",false,LocalDate.now().plusDays(5));
    }

    private Task add(String title, String description, boolean complete, LocalDate dueDate){
        long id = nextId.getAndIncrement();
        Task task = new Task(id, title, description, complete, dueDate);
        tasks.put(id, task);
        return task;
    }

    public List<Task> findAll(){
        return new ArrayList<>(tasks.values());
    }

    public Optional<Task> findById(Long id){
        return Optional.ofNullable(tasks.get(id));
    }

    public Task create(TaskRequestDto requestDto){
        return add(requestDto.title(),requestDto.description(), requestDto.complete(), requestDto.dueDate());
    }
}
