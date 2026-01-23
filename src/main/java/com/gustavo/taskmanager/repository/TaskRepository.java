package com.gustavo.taskmanager.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gustavo.taskmanager.entity.Task;
import com.gustavo.taskmanager.entity.TaskPriority;
import com.gustavo.taskmanager.entity.TaskStatus;

public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("""
        select t from Task t
        where
          (:status is null or t.status = :status)
          and (:priority is null or t.priority = :priority)
          and (
            lower(t.title) like :q
            or lower(coalesce(t.description, '')) like :q
          )
    """)
    Page<Task> search(
            @Param("q") String q,
            @Param("status") TaskStatus status,
            @Param("priority") TaskPriority priority,
            Pageable pageable
    );

    @Query("""
        select t from Task t
        where
          (:status is null or t.status = :status)
          and (:priority is null or t.priority = :priority)
    """)
    Page<Task> filterOnly(
            @Param("status") TaskStatus status,
            @Param("priority") TaskPriority priority,
            Pageable pageable
    );
}
