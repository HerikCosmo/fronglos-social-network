package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.dto.request.RegisterUserRequest;
import cosmo.herik.fronglos.dto.response.RegisterUserResponse;
import cosmo.herik.fronglos.entity.User;
import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterUserResponse register(RegisterUserRequest request) {
        User newUser = new User();
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setEmail(request.email());
        newUser.setUsername(request.username());

        userRepository.save(newUser);

        return new RegisterUserResponse(newUser.getUsername(), newUser.getEmail());
    }
}
