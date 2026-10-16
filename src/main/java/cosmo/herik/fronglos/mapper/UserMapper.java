package cosmo.herik.fronglos.mapper;

import cosmo.herik.fronglos.dto.response.AuthUserProfileResponse;
import cosmo.herik.fronglos.dto.response.UserProfileResponse;
import cosmo.herik.fronglos.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "username", source = "user.publicUsername")
    @Mapping(target = "numberFollowers", source = "numberFollowers")
    @Mapping(target = "numberFollowing", source = "numberFollowing")
    AuthUserProfileResponse toAuthUserProfileResponse(User user, Long numberFollowers, Long numberFollowing);

    @Mapping(target = "username", source = "user.publicUsername")
    @Mapping(target = "numberFollowers", source = "numberFollowers")
    @Mapping(target = "numberFollowing", source = "numberFollowing")
    UserProfileResponse toUserProfile(User user, Long numberFollowers, Long numberFollowing);
}
