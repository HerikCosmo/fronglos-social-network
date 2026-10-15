package cosmo.herik.fronglos.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostRequest(
        @NotBlank(message = "Content is required")
        @Size(max = 280, message = "Content must not exceed 280 characters")
        String content
) {
}
