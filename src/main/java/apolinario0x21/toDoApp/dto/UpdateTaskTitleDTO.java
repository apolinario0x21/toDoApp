package apolinario0x21.toDoApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateTaskTitleDTO(
        @NotBlank(message = "Título não pode ser nulo ou vazio")
        @Size(min = 5, max = 32, message = "Título deve ter entre 5 e 32 caracteres")
        String title
) {
    // @Size(min = 5, max = 32, message = "Title must be between 5 and 32 characters")
}
