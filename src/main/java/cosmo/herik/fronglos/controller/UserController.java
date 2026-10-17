package cosmo.herik.fronglos.controller;

import cosmo.herik.fronglos.docs.UserControllerDocs;
import cosmo.herik.fronglos.dto.response.AuthUserProfileResponse;
import cosmo.herik.fronglos.dto.response.PostResponse;
import cosmo.herik.fronglos.dto.response.SimpleUserProfileResponse;
import cosmo.herik.fronglos.dto.response.UserProfileResponse;
import cosmo.herik.fronglos.service.FollowService;
import cosmo.herik.fronglos.service.PostService;
import cosmo.herik.fronglos.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController implements UserControllerDocs {
    private final UserService userService;
    private final PostService postService;
    private final FollowService followService;

    public UserController(UserService userService, PostService postService, FollowService followService) {
        this.userService = userService;
        this.postService = postService;
        this.followService = followService;
    }

    @Override
    @GetMapping("/me")
    public AuthUserProfileResponse getCurrentUser() {
        return userService.getAuthUserProfile();
    }

    @Override
    @GetMapping("/{id}")
    public UserProfileResponse getUserById(@PathVariable UUID id) {
        return userService.getUserProfile(id);
    }

    @Override
    @GetMapping("/{id}/posts")
    public Page<PostResponse> getPostsFromUserId(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return postService.getPostsFromUserId(id, page, size);
    }

    @Override
    @PostMapping("/{id}/follow")
    @ResponseStatus(HttpStatus.CREATED)
    public void followerUser(@PathVariable UUID id) {
        followService.follow(id);
    }

    @Override
    @DeleteMapping("/{id}/follow")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unfollowUser(@PathVariable UUID id) {
        followService.unfollow(id);
    }

    @Override
    @GetMapping("/{id}/following")
    public Page<SimpleUserProfileResponse> getFollowing(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return followService.getFollowing(id, page, size);
    }

    @Override
    @GetMapping("/{id}/followers")
    public Page<SimpleUserProfileResponse> getFollowers(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return followService.getFollowers(id, page, size);
    }
}
