package com.shafrin.taskmanagementapi.dto;

import java.time.LocalDate;

import com.shafrin.taskmanagementapi.entity.TaskStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotBlank
    private String title;

    private String description;

    private TaskStatus status;

    private LocalDate dueDate;

    private Long userId;
}