package cosmo.herik.fronglos.dto.response;

import java.util.UUID;

public record AuthUserProfileResponse(
        UUID id,
        String displayName,
        String username,
        String email,
        Integer numberFollowers,
        Integer numberFollowing
){
}
