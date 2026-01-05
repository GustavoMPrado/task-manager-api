package com.gustavo.taskmanager.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.gustavo.taskmanager.dto.TaskCreateDTO;
import com.gustavo.taskmanager.dto.TaskPatchDTO;
import com.gustavo.taskmanager.dto.TaskResponseDTO;
import com.gustavo.taskmanager.dto.TaskUpdateDTO;
import com.gustavo.taskmanager.entity.Task;
import com.gustavo.taskmanager.entity.TaskPriority;
import com.gustavo.taskmanager.exception.TaskNotFoundException;
import com.gustavo.taskmanager.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // CREATE
    public Task create(TaskCreateDTO dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());

        if (dto.getStatus() != null) {
            task.setStatus(dto.getStatus());
        }

        if (dto.getPriority() != null) {
            task.setPriority(dto.getPriority());
        } else {
            task.setPriority(TaskPriority.MEDIUM);
        }

        task.setDueDate(dto.getDueDate());

        return taskRepository.save(task);
    }

    // READ ALL (sem paginação) - pode manter, mas não vamos usar no controller principal
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    // READ ALL (com paginação)
    public Page<TaskResponseDTO> findAll(Pageable pageable) {
        return taskRepository.findAll(pageable).map(this::toResponseDTO);
    }

    // READ BY ID (entity)
    public Task findById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }

    // MAPPER (Entity -> ResponseDTO)
    public TaskResponseDTO toResponseDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        dto.setCreatedAt(task.getCreatedAt());
        dto.setUpdatedAt(task.getUpdatedAt());
        return dto;
    }

    // UPDATE (PUT - completo)
    public TaskResponseDTO update(Long id, TaskUpdateDTO dto) {
        Task task = findById(id);

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());

        if (dto.getStatus() != null) {
            task.setStatus(dto.getStatus());
        }

        if (dto.getPriority() != null) {
            task.setPriority(dto.getPriority());
        } else {
            task.setPriority(TaskPriority.MEDIUM);
        }

        task.setDueDate(dto.getDueDate());

        Task saved = taskRepository.save(task);
        return toResponseDTO(saved);
    }

    // PATCH (parcial)
    public TaskResponseDTO patch(Long id, TaskPatchDTO dto) {
        Task task = findById(id);

        if (dto.getTitle() != null) task.setTitle(dto.getTitle());
        if (dto.getDescription() != null) task.setDescription(dto.getDescription());
        if (dto.getStatus() != null) task.setStatus(dto.getStatus());
        if (dto.getPriority() != null) task.setPriority(dto.getPriority());
        if (dto.getDueDate() != null) task.setDueDate(dto.getDueDate());

        Task saved = taskRepository.save(task);
        return toResponseDTO(saved);
    }

    // DELETE
    public void delete(Long id) {
        Task task = findById(id);
        taskRepository.delete(task);
    }
}


