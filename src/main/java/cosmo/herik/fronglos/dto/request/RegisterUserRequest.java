package cosmo.herik.fronglos.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserRequest(
        @NotBlank(message = "Email is required")
        @Email(message = "Email must be valid")
        String email,
        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be at least 8 characters long")
        @Pattern(
                regexp = "^(?=.*[a-zA-Z])(?=.*[0-9]).+$",
                message = "Password must contain at least one letter and one number"
        )
        String password,
        @NotBlank(message = "Username is required")
        @Pattern(
                regexp = "^[a-zA-Z0-9_]{3,20}$",
                message = "Username must be between 3 and 20 characters and contain only letters, numbers and underscores"
        )
        String username,
        @Size(max = 50, message = "Display name must not exceed 50 characters")
        String displayName
) {
}
