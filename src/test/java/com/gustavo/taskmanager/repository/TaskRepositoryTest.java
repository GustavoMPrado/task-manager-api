package com.gustavo.taskmanager.repository;

import static org.assertj.core.api.Assertions.assertThat;

import com.gustavo.taskmanager.entity.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void saveAndFindById_shouldWork() {
        Task t = new Task();
        t.setTitle("Repo test");
        t.setDescription("ok");

        Task saved = taskRepository.save(t);

        var found = taskRepository.findById(saved.getId());
        assertThat(found).isPresent();
        assertThat(found.get().getTitle()).isEqualTo("Repo test");
    }
}
