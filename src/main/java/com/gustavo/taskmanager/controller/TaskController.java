package com.gustavo.taskmanager.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.gustavo.taskmanager.dto.TaskCreateDTO;
import com.gustavo.taskmanager.dto.TaskPatchDTO;
import com.gustavo.taskmanager.dto.TaskResponseDTO;
import com.gustavo.taskmanager.dto.TaskUpdateDTO;
import com.gustavo.taskmanager.entity.Task;
import com.gustavo.taskmanager.entity.TaskPriority;
import com.gustavo.taskmanager.entity.TaskStatus;
import com.gustavo.taskmanager.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 50;

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO create(@Valid @RequestBody TaskCreateDTO dto) {
        Task created = taskService.create(dto);
        return taskService.toResponseDTO(created);
    }

    @GetMapping
    public Page<TaskResponseDTO> list(
            @RequestParam(required = false) String q,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskPriority priority,
            @RequestParam(defaultValue = "" + DEFAULT_PAGE) int page,
            @RequestParam(defaultValue = "" + DEFAULT_SIZE) int size
    ) {
        int safePage = Math.max(page, 0);
        int safeSize = Math.min(Math.max(size, 1), MAX_SIZE);
        Pageable pageable = PageRequest.of(safePage, safeSize);
        return taskService.search(q, status, priority, pageable);
    }

    @GetMapping("/{id}")
    public TaskResponseDTO getById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return taskService.toResponseDTO(task);
    }

    @PutMapping("/{id}")
    public TaskResponseDTO update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO dto) {
        return taskService.update(id, dto);
    }

    @PatchMapping("/{id}")
    public TaskResponseDTO patch(@PathVariable Long id, @Valid @RequestBody TaskPatchDTO dto) {
        return taskService.patch(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}






