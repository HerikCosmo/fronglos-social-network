package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.entity.Follow;
import cosmo.herik.fronglos.entity.User;
import cosmo.herik.fronglos.exception.ResourceAlreadyExistsException;
import cosmo.herik.fronglos.exception.SelfFollowException;
import cosmo.herik.fronglos.repository.FollowRepository;
import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FollowService {
    private final CurrentUserService currentUserService;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;

    public FollowService(
            FollowRepository followRepository,
            CurrentUserService currentUserService,
            UserRepository userRepository) {
        this.followRepository = followRepository;
        this.currentUserService = currentUserService;
        this.userRepository = userRepository;
    }

    public void follow(UUID followingId) {
        User followerUser = currentUserService.getCurrentUser();

        if(followerUser.getId().equals(followingId)) {
            throw new SelfFollowException();
        }

        User followingUser = userRepository.findById(followingId)
                .orElseThrow(() -> new UsernameNotFoundException(followingId.toString()));

        if(followRepository.existsByFollowerIdAndFollowingId(followerUser.getId(), followingId)) {
            throw new ResourceAlreadyExistsException("The current user already follow this user");
        }

        followRepository.save(new Follow(followerUser, followingUser));
    }
}
