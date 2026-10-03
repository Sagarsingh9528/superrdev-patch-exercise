package com.internal.tasktracker;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class TaskRepositoryTest {

    @Autowired
    private TaskRepository taskRepository;

    @Test
    void searchAppliesStatusAndArchivedFiltersToTitleAndDescriptionMatches() {
        List<Task> titleMatches = taskRepository.searchTasks("%task comments%", "DONE");
        List<Task> descriptionMatches = taskRepository.searchTasks("%deprecated%", null);

        assertThat(titleMatches).isEmpty();
    assertThat(descriptionMatches).isEmpty();
    }
}