package apolinario0x21.toDoApp.service;

import apolinario0x21.toDoApp.dto.TaskRequestDTO;
import apolinario0x21.toDoApp.dto.TaskResponseDTO;
import apolinario0x21.toDoApp.exceptions.TaskNotFoundException;
import apolinario0x21.toDoApp.model.Task;
import apolinario0x21.toDoApp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public TaskResponseDTO toResponse(Task task) {

        return new TaskResponseDTO(
                task.getId(),
                task.getTitle(),
                task.isCompleted(),
                task.getCreatedAt());
    }

    public TaskResponseDTO createTask(TaskRequestDTO request) {

        Task task = new Task();
        task.setTitle(request.title());
        task.setCompleted(false);
        Task savedTask = taskRepository.save(task);
        return toResponse(savedTask);
    }

    public List<TaskResponseDTO> findAllTasks() {
        return taskRepository.
                findAllByOrderByCreatedAtDesc()
                .stream().map(this::toResponse).toList();
    }
    //getTaskById(UUID id)

    private Task findById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found. ID:" + id));
    }

    public TaskResponseDTO findTaskById(UUID id) {
        Task task = findById(id);
        return toResponse(task);
    }

    public void titleUpdateTask(UUID id, String newTitle) {
        Task task = findById(id);
        task.setTitle(newTitle);
        taskRepository.save(task);
    }

    public void statusUpdateTask(UUID id, boolean completed) {
        Task task = findById(id);
        task.setCompleted(completed);
        taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found. ID:" + id);
        }

        taskRepository.deleteById(id);
    }


}
