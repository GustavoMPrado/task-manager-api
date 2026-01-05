package com.gustavo.taskmanager.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.gustavo.taskmanager.dto.TaskCreateDTO;
import com.gustavo.taskmanager.dto.TaskPatchDTO;
import com.gustavo.taskmanager.dto.TaskResponseDTO;
import com.gustavo.taskmanager.dto.TaskUpdateDTO;
import com.gustavo.taskmanager.entity.Task;
import com.gustavo.taskmanager.service.TaskService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // POST /tasks
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TaskResponseDTO create(@Valid @RequestBody TaskCreateDTO dto) {
        Task created = taskService.create(dto);
        return taskService.toResponseDTO(created);
    }

    // GET /tasks (com paginação/ordenação)
    @GetMapping
    public Page<TaskResponseDTO> list(Pageable pageable) {
        return taskService.findAll(pageable);
    }

    // GET /tasks/{id}
    @GetMapping("/{id}")
    public TaskResponseDTO getById(@PathVariable Long id) {
        Task task = taskService.findById(id);
        return taskService.toResponseDTO(task);
    }

    // PUT /tasks/{id}
    @PutMapping("/{id}")
    public TaskResponseDTO update(@PathVariable Long id, @Valid @RequestBody TaskUpdateDTO dto) {
        return taskService.update(id, dto);
    }

    // PATCH /tasks/{id}
    @PatchMapping("/{id}")
    public TaskResponseDTO patch(@PathVariable Long id, @Valid @RequestBody TaskPatchDTO dto) {
        return taskService.patch(id, dto);
    }

    // DELETE /tasks/{id}
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        taskService.delete(id);
    }
}





