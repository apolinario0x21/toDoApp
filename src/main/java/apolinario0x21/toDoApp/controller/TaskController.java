package apolinario0x21.toDoApp.controller;

import apolinario0x21.toDoApp.dto.TaskRequestDTO;
import apolinario0x21.toDoApp.dto.TaskResponseDTO;
import apolinario0x21.toDoApp.dto.TaskStatusUpdateRequestDTO;
import apolinario0x21.toDoApp.dto.UpdateTaskTitleDTO;
import apolinario0x21.toDoApp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/*
    @CrossOrigin(origins = "http://localhost:5173") permitir que o frontend acesse a API
*/

@RestController
@RequestMapping("/api/tasks")
@Tag(name = "Task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Operation(summary = "Create a new task")
    @PostMapping
    public ResponseEntity<TaskResponseDTO> createTask(
            @Valid @RequestBody TaskRequestDTO request) { // @RequestBody indica que o corpo da requisição contém os dados do novo task

        TaskResponseDTO response = taskService.createTask(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Operation(summary = "Get all task")
    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> findAllTasks() {
        return ResponseEntity.ok(taskService.findAllTasks());
    }

    @Operation(summary = "Update a task title")
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTitle(@PathVariable UUID id,
                                            @Valid @RequestBody UpdateTaskTitleDTO update) {
        taskService.titleUpdateTask(id, update.title());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Update a task status")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id,
                                             @Valid @RequestBody TaskStatusUpdateRequestDTO update) {
        taskService.statusUpdateTask(id, update.completed());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Operation(summary = "Delete a task by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable UUID id) { // @PathVariable indica que o id é um parâmetro da URL
        taskService.deleteTask(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
