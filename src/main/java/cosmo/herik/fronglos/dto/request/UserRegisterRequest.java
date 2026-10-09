package cosmo.herik.fronglos.dto.request;

import jakarta.validation.constraints.Email;

public record UserRegisterRequest(
        @Email
        String email,
        String password,
        String username
) {
}
