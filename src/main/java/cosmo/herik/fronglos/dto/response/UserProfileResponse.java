package cosmo.herik.fronglos.dto.response;

import java.util.UUID;

public record UserProfileResponse(

        String displayName,
        String username,
        Integer numberFollowers,
        Integer numberFollowing
){
}
