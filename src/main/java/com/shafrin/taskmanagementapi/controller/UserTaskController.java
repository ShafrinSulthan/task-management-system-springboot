package com.shafrin.taskmanagementapi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shafrin.taskmanagementapi.entity.Task;
import com.shafrin.taskmanagementapi.service.TaskService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user-tasks")
@RequiredArgsConstructor
public class UserTaskController {

    private final TaskService taskService;

    @GetMapping("/{userId}")
    public List<Task> getTasksByUser(
            @PathVariable Long userId) {

        return taskService.getTasksByUserId(userId);
    }
}