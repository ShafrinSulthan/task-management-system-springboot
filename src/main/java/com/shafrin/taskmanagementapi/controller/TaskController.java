package com.shafrin.taskmanagementapi.controller;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shafrin.taskmanagementapi.dto.CreateTaskRequest;
import com.shafrin.taskmanagementapi.entity.Task;
import com.shafrin.taskmanagementapi.service.TaskService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@Tag(name = "Task Management API", description = "Operations related to task management")
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    @Operation(summary = "Create New Task")
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public String createTask(
            @Valid @RequestBody CreateTaskRequest request) {

        return taskService.createTask(request);
    }
    @Operation(summary = "Get Task By Id")
    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {

        return taskService.getTaskById(id);
    }
    @Operation(summary = "Update Task")
    @PutMapping("/{id}")
    public String updateTask(
            @PathVariable Long id,
            @RequestBody CreateTaskRequest request) {

        return taskService.updateTask(id, request);
    }
    @Operation(summary = "Delete Task")
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public String deleteTask(
            @PathVariable Long id) {

        return taskService.deleteTask(id);
    }
    @Operation(summary = "Get All Tasks")
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }
    @Operation(summary = "Get Tasks By Status")
    @GetMapping("/status/{status}")
    public List<Task> getTasksByStatus(
            @PathVariable String status) {

        return taskService.getTasksByStatus(status);
    }
    @Operation(summary = "Get Tasks By User Id")
    @GetMapping("/user/{userId}")
    public List<Task> getTasksByUserId(
            @PathVariable Long userId) {

        return taskService.getTasksByUserId(userId);
    }
}