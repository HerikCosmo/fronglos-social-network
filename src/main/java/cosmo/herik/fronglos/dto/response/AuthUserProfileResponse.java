package cosmo.herik.fronglos.dto.response;

import java.util.UUID;

public record AuthUserProfileResponse(
        UUID id,
        String displayName,
        String username,
        String email,
        Long numberFollowers,
        Long numberFollowing
){
}
