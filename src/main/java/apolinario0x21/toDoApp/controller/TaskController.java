package apolinario0x21.toDoApp.controller;

import apolinario0x21.toDoApp.model.Task;
import apolinario0x21.toDoApp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173") // permitir que o frontend acesse a API
@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) { // @RequestBody indica que o corpo da requisição contém os dados do novo task
        return taskService.createTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Long id) { // @PathVariable indica que o id é um parâmetro da URL
        taskService.deleteTask(id);
    }
}
