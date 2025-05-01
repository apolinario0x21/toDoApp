package apolinario0x21.toDoApp.dto;

import jakarta.validation.constraints.NotBlank;

public class UpdateTaskTitleDTO {

    @NotBlank(message = "Title cannot be blank")
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
