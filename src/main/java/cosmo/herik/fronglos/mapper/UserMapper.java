package cosmo.herik.fronglos.mapper;

import cosmo.herik.fronglos.dto.request.RegisterUserRequest;
import cosmo.herik.fronglos.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User userRegisterRequestToEntity(RegisterUserRequest registerUserRequest);
}
