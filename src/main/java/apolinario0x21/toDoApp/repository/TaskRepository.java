package apolinario0x21.toDoApp.repository;

import apolinario0x21.toDoApp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findAllByOrderByCreatedAtDesc();

}
