package com.shafrin.taskmanagementapi.service;

import java.util.List;

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
    public String createTask(CreateTaskRequest request) {

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

        return "Task Created Successfully";
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {

        return taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task Not Found"));
    }

    public String updateTask(Long id,
                     CreateTaskRequest request) {

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

        return "Task Updated Successfully";
    }

    public String deleteTask(Long id) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Task Not Found"));

        taskRepository.delete(task);

        return "Task Deleted Successfully";
    }

    public List<Task> getTasksByStatus(String status) {

        return taskRepository.findByStatus(status);
    }
    public List<Task> getTasksByUserId(Long userId) {

        return taskRepository.findByUserId(userId);
    }
}