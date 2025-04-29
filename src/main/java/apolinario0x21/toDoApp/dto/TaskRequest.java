package apolinario0x21.toDoApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequest {
    @NotBlank
    @Size(max = 32, message = "Title must be less than 32 characters")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
