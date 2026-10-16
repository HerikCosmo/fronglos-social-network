package cosmo.herik.fronglos.dto.response;

public record UserProfileResponse(
        String displayName,
        String username,
        Integer numberFollowers,
        Integer numberFollowing
){
}
