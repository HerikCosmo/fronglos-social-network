package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.config.JwtService;
import cosmo.herik.fronglos.dto.response.AuthUserProfileResponse;
import cosmo.herik.fronglos.dto.response.UserProfileResponse;
import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final CurrentUserService currentUserService;

    public UserService(UserRepository userRepository, CurrentUserService currentUserService) {
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
    }

    public AuthUserProfileResponse getAuthUserProfile() {
        var user = currentUserService.getCurrentUser();

        return new AuthUserProfileResponse(
                user.getId(),
                user.getDisplayName(),
                user.getPublicUsername(),
                user.getEmail(),
                0,
                0
        );
    }

    public UserProfileResponse getUserProfile(UUID id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));

        return new UserProfileResponse(
                user.getDisplayName(),
                user.getPublicUsername(),
                0,
                0
        );
    }
}
