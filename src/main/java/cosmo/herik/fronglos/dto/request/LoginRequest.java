package cosmo.herik.fronglos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record LoginRequest(
        @NotEmpty(message = "E-email é obrigatório")
        @Email
        String email,
        @NotEmpty(message = "Senha é obrigatório")
        String password
) {
}
