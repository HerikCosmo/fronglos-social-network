package cosmo.herik.fronglos.dto.response;

import java.util.UUID;

public record SimpleUserProfileResponse(UUID id, String username, String displayName){}
