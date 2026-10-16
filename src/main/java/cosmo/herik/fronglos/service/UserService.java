package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.dto.response.AuthUserProfileResponse;
import cosmo.herik.fronglos.dto.response.UserProfileResponse;
import cosmo.herik.fronglos.mapper.UserMapper;
import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserService(UserRepository userRepository, CurrentUserService currentUserService) {
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
    }

    public AuthUserProfileResponse getAuthUserProfile() {
        var user = currentUserService.getCurrentUser();

        return userMapper.toAuthUserProfileResponse(user, 0, 0);
    }

    public UserProfileResponse getUserProfile(UUID id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));

        return userMapper.toUserProfile(user, 0, 0);
    }
}
