package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.dto.response.SimpleUserProfileResponse;
import cosmo.herik.fronglos.entity.Follow;
import cosmo.herik.fronglos.entity.User;
import cosmo.herik.fronglos.exception.NotFollowingException;
import cosmo.herik.fronglos.exception.ResourceAlreadyExistsException;
import cosmo.herik.fronglos.exception.SelfFollowException;
import cosmo.herik.fronglos.mapper.UserMapper;
import cosmo.herik.fronglos.repository.FollowRepository;
import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FollowService {
    private final CurrentUserService currentUserService;
    private final FollowRepository followRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper = UserMapper.INSTANCE;

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

    @Transactional
    public void unfollow(UUID followingId) {
        User followerUser = currentUserService.getCurrentUser();

        userRepository.findById(followingId)
                    .orElseThrow(() -> new UsernameNotFoundException(followingId.toString()));

        if(!followRepository.existsByFollowerIdAndFollowingId(followerUser.getId(), followingId)) {
            throw new NotFollowingException();
        }

        followRepository.deleteByFollowerIdAndFollowingId(followerUser.getId(), followingId);
    }

    public Page<SimpleUserProfileResponse> getFollowing(UUID id, int page, int size) {
        userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("followedAt").descending()
        );

        Page<Follow> followings = followRepository.findByFollowerId(id, pageable);

        return followings.map(
                follow -> userMapper.toSimpleUserProfile(follow.getFollowing())
        );
    }

    public Page<SimpleUserProfileResponse> getFollowers(UUID id, int page, int size) {
        userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("followedAt").descending()
        );

        Page<Follow> followers = followRepository.findByFollowingId(id, pageable);

        return followers.map(
                follow -> userMapper.toSimpleUserProfile(follow.getFollower())
        );
    }
}
