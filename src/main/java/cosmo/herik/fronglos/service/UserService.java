package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.dto.request.UserRegisterRequest;
import cosmo.herik.fronglos.mapper.UserMapper;
import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRegisterRequest userRegisterRequest) {
        var user = userMapper.userRegisterRequestToEntity(userRegisterRequest);

        // processo de gerar senha...

        userRepository.save(user);
    }
}
