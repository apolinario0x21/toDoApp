package apolinario0x21.toDoApp.service;

import apolinario0x21.toDoApp.dto.TaskRequestDTO;
import apolinario0x21.toDoApp.dto.TaskResponseDTO;
import apolinario0x21.toDoApp.exceptions.TaskNotFoundException;
import apolinario0x21.toDoApp.model.Task;
import apolinario0x21.toDoApp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

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

        logger.info("Criando uma nova tarefa: {}", request.title());

        Task task = new Task();
        task.setTitle(request.title());
        task.setCompleted(false);
        Task savedTask = taskRepository.save(task);

        logger.info("Tarefa criada com sucesso: {}", savedTask.getId());
        return toResponse(savedTask);
    }

    public List<TaskResponseDTO> findAllTasks() {

        logger.info("Buscando todas as tarefas");

        return taskRepository.
                findAllByOrderByCreatedAtDesc()
                .stream().map(this::toResponse).toList();
    }

    private Task findById(UUID id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> {

                    logger.error("Tarefa não encontrada. ID: {}", id);
                    return new TaskNotFoundException("Tarefa não encontrada. ID: " + id);
                });
    }

    public TaskResponseDTO findTaskById(UUID id) {

        logger.info("Buscando tarefa por ID: {}", id);

        Task task = findById(id);
        return toResponse(task);
    }

    public void titleUpdateTask(UUID id, String newTitle) {

        logger.info("Atualizando o titulo da tarefa com ID: {}. Novo título: {}", id, newTitle);

        Task task = findById(id);
        task.setTitle(newTitle);
        taskRepository.save(task);

        logger.info("Título da tarefa com ID: {} atualizado com sucesso", id);
    }

    public void statusUpdateTask(UUID id, boolean completed) {

        logger.info("Atualizando o status da tarefa com ID {}. Concluída: {}", id, completed);

        Task task = findById(id);
        task.setCompleted(completed);
        taskRepository.save(task);

        logger.info("Status da tarefa com ID: {} atualizado com sucesso", id);
    }

    public void deleteTask(UUID id) {

        logger.info("Deletando tarefa com ID: {}", id);

        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found. ID:" + id);
        }

        taskRepository.deleteById(id);

        logger.info("Tarefa com ID: {} deletada com sucesso", id);
    }


}
