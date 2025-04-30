package apolinario0x21.toDoApp.service;

import apolinario0x21.toDoApp.dto.TaskRequest;
import apolinario0x21.toDoApp.exceptions.TaskNotFoundException;
import apolinario0x21.toDoApp.model.Task;
import apolinario0x21.toDoApp.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks() {
        return taskRepository.findAllByOrderByCreatedAtDesc();
    }

    public Task createTask(TaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        return taskRepository.save(task);
    }

    public TaskRequest getTaskById(UUID id) {return null;}

    public void updateTask(UUID id, TaskRequest request) {}

    public void updateTaskStatus(UUID id, boolean completed) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
        task.setCompleted(completed);
        taskRepository.save(task);
    }

    public void deleteTask(UUID id) {
        taskRepository.deleteById(id); // deleta a tarefa com o id especificado
        // elemento existe?
    }


}
