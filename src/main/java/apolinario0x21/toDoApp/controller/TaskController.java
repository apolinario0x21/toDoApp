package apolinario0x21.toDoApp.controller;

import apolinario0x21.toDoApp.dto.TaskRequest;
import apolinario0x21.toDoApp.model.Task;
import apolinario0x21.toDoApp.repository.TaskRepository;
import apolinario0x21.toDoApp.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:5173") // permitir que o frontend acesse a API
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks(); // retorna a lista de tarefas
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody TaskRequest request) { // @RequestBody indica que o corpo da requisição contém os dados do novo task
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(request));
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable UUID id) { // @PathVariable indica que o id é um parâmetro da URL
        taskService.deleteTask(id);
    }
}
