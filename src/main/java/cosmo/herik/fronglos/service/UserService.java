package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.config.JwtService;
import cosmo.herik.fronglos.dto.response.AuthUserProfileResponse;
import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public UserService(UserRepository userRepository, JwtService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public AuthUserProfileResponse getAuthUserProfile(String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length());
        String email = jwtService.extractUsername(token);

        var user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));

        return new AuthUserProfileResponse(
                user.getId(),
                user.getDisplayName(),
                user.getPublicUsername(),
                user.getEmail(),
                0,
                0
        );
    }
}
