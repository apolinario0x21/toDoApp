package apolinario0x21.toDoApp.service;

import apolinario0x21.toDoApp.dto.TaskRequestDTO;
import apolinario0x21.toDoApp.dto.TaskResponseDTO;
import apolinario0x21.toDoApp.exceptions.TaskNotFoundException;
import apolinario0x21.toDoApp.model.Task;
import apolinario0x21.toDoApp.repository.TaskRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Task service Unit Tests")
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    TaskService taskService;

    @Test
    @DisplayName("Test create task")
    void createNewTask() {
        TaskRequestDTO requestDTO = new TaskRequestDTO("Nova tarefa");

        Task mockTask = new Task();
        mockTask.setId(UUID.randomUUID());
        mockTask.setTitle(requestDTO.title());
        mockTask.setCompleted(false);
        mockTask.setCreatedAt(Instant.now());

        when(taskRepository.save(any(Task.class))).thenReturn(mockTask);

        TaskResponseDTO result = taskService.createTask(requestDTO);

        assertEquals(mockTask.getId(), result.id());
        assertEquals(mockTask.getTitle(), result.title());
        assertEquals(mockTask.isCompleted(), result.completed());
    }

    @Test
    @DisplayName("Find all tasks")
    void findAllTasks() {

        Task task1 = new Task();
        task1.setId(UUID.randomUUID());
        task1.setTitle("Tarefa 1");
        task1.setCreatedAt(Instant.now());

        Task task2 = new Task();
        task2.setId(UUID.randomUUID());
        task2.setTitle("Tarefa 2");
        task2.setCreatedAt(Instant.now());

        List<Task> mockTasks = List.of(task1, task2);

        when(taskRepository.findAllByOrderByCreatedAtDesc()).thenReturn(mockTasks);

        List<TaskResponseDTO> result = taskService.findAllTasks();

        assertFalse(result.isEmpty());
        assertEquals(2, result.size());
        assertEquals("Tarefa 1", result.get(0).title());
        assertEquals("Tarefa 2", result.get(1).title());
    }

    @Test
    @DisplayName("Find a task by ID")
    void findTaskById() {

        UUID taskId = UUID.randomUUID();
        Task mockTask = new Task();
        mockTask.setId(taskId);
        mockTask.setTitle("Tarefa de busca");

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockTask));
        TaskResponseDTO result = taskService.findTaskById(taskId);

        assertNotNull(result);
        assertEquals(taskId, result.id());
        assertEquals("Tarefa de busca", result.title());
    }

    @Test
    @DisplayName("Throw TaskNotFoundException when finding a non-existent task")
    void throwExceptionWhenFindingNonExistentTask() {

        UUID taskId = UUID.randomUUID();
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());
        assertThrows(TaskNotFoundException.class, () -> taskService.findTaskById(taskId));
    }

    @Test
    @DisplayName("Update task title")
    void updateTaskTitle() {

        UUID taskId = UUID.randomUUID();
        String newTitle = "Novo título da tarefa";

        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setTitle("Título antigo");
        existingTask.setCompleted(false);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        taskService.titleUpdateTask(taskId, newTitle);
        assertEquals(newTitle, existingTask.getTitle());
    }


    @Test
    @DisplayName("Update task status")
    void updateTaskStatus() {

        UUID taskId = UUID.randomUUID();
        boolean newStatus = true;

        Task existingTask = new Task();
        existingTask.setId(taskId);
        existingTask.setCompleted(false);

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        taskService.statusUpdateTask(taskId, newStatus);
        assertTrue(existingTask.isCompleted());
    }

    @Test
    @DisplayName("Delete a task")
    void deleteTask() {

        UUID taskId = UUID.randomUUID();
        when(taskRepository.existsById(taskId)).thenReturn(true);
        doNothing().when(taskRepository).deleteById(taskId);
        assertDoesNotThrow(() -> taskService.deleteTask(taskId));
        verify(taskRepository, times(1)).deleteById(taskId);
    }

    @Test
    @DisplayName("Throw TaskNotFoundException when deleting a non-existent task")
    void throwExceptionWhenDeletingNonExistentTask() {

        UUID taskId = UUID.randomUUID();
        when(taskRepository.existsById(taskId)).thenReturn(false);
        assertThrows(TaskNotFoundException.class, () -> taskService.deleteTask(taskId));
    }
}
