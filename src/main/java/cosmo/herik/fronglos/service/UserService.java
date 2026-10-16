package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.dto.response.AuthUserProfileResponse;
import cosmo.herik.fronglos.dto.response.UserProfileResponse;
import cosmo.herik.fronglos.mapper.UserMapper;
import cosmo.herik.fronglos.repository.FollowRepository;
import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final FollowRepository followRepository;
    private final CurrentUserService currentUserService;
    private final UserMapper userMapper = UserMapper.INSTANCE;

    public UserService(
            UserRepository userRepository,
            CurrentUserService currentUserService,
            FollowRepository followRepository
    ) {
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.followRepository = followRepository;
    }

    public AuthUserProfileResponse getAuthUserProfile() {
        var user = currentUserService.getCurrentUser();

        long numberFollowers = followRepository.countByFollowingId(user.getId());
        long numberFollowing = followRepository.countByFollowerId(user.getId());

        return userMapper.toAuthUserProfileResponse(user, numberFollowers, numberFollowing);
    }

    public UserProfileResponse getUserProfile(UUID id) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));

        long numberFollowers = followRepository.countByFollowingId(user.getId());
        long numberFollowing = followRepository.countByFollowerId(user.getId());

        return userMapper.toUserProfile(user, numberFollowers, numberFollowing);
    }
}
