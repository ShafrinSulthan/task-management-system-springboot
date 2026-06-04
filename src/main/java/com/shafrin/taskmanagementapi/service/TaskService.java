package com.shafrin.taskmanagementapi.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.shafrin.taskmanagementapi.dto.CreateTaskRequest;
import com.shafrin.taskmanagementapi.entity.Task;
import com.shafrin.taskmanagementapi.entity.User;
import com.shafrin.taskmanagementapi.repository.TaskRepository;
import com.shafrin.taskmanagementapi.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);
    
    public String createTask(CreateTaskRequest request) {

        logger.info("Creating new task: {}", request.getTitle());

        Task task = new Task();

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        task.setUser(user);
        taskRepository.save(task);

        logger.info("Task created successfully");

        return "Task Created Successfully";
    }

    public List<Task> getAllTasks() {
        logger.info("Fetching all tasks");
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        logger.info("Fetching task with id: {}", id);
        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task Not Found"));
    }

    public String updateTask(Long id,
                     CreateTaskRequest request) {
        logger.info("Updating task with id: {}", id);

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task Not Found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setStatus(request.getStatus());
        task.setDueDate(request.getDueDate());

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() ->
                        new RuntimeException("User Not Found"));

        task.setUser(user);

        taskRepository.save(task);

        logger.info("Task updated successfully with id: {}", id);

        return "Task Updated Successfully";
    }

    public String deleteTask(Long id) {

        logger.info("Deleting task with id: {}", id);

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task Not Found"));

        taskRepository.delete(task);

        logger.info("Task deleted successfully with id: {}", id);

        return "Task Deleted Successfully";
    }

    public List<Task> getTasksByStatus(String status) {

        return taskRepository.findByStatus(status);
    }
    public List<Task> getTasksByUserId(Long userId) {

        return taskRepository.findByUserId(userId);
    }
}