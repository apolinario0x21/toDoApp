package apolinario0x21.toDoApp.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public class TaskStatusUpdateRequest {

    @NotNull
    private UUID id;

    @NotNull
    private boolean completed;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
