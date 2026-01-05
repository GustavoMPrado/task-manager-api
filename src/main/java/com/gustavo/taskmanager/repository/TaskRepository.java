package com.gustavo.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gustavo.taskmanager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
