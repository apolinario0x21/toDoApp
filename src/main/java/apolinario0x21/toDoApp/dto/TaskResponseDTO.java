package apolinario0x21.toDoApp.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;
import java.util.UUID;

public record TaskResponseDTO(
        @NotBlank(message = "ID não pode ser nulo ou vazio")
        UUID id,

        @NotBlank(message = "Título não pode ser nulo ou vazio")
        @Size(min = 5, max = 32, message = "Título deve ter entre 5 e 32 caracteres")
        String title, boolean completed, Instant createdAt
) {

}
