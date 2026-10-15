package cosmo.herik.fronglos.dto.response;

import java.time.Instant;
import java.util.UUID;

public record PostResponse(UUID id, String content, Instant createdAt, AuthorResponse author) {
    public record AuthorResponse(UUID id, String displayName, String username) {}
}
