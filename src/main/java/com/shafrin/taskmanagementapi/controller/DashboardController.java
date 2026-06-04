package com.shafrin.taskmanagementapi.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shafrin.taskmanagementapi.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final TaskRepository taskRepository;

    @GetMapping("/api/dashboard")
public Map<String, Long> dashboard() {

    Map<String, Long> data = new HashMap<>();

    data.put("totalTasks",
            taskRepository.count());

    data.put("pendingTasks",
            taskRepository.countByStatus("Pending"));

    data.put("completedTasks",
            taskRepository.countByStatus("Completed"));

    data.put("inProgressTasks",
            taskRepository.countByStatus("In Progress"));

    return data;
}
}