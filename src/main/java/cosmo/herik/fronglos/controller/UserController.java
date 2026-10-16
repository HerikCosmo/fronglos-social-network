package cosmo.herik.fronglos.controller;

import cosmo.herik.fronglos.dto.response.AuthUserProfileResponse;
import cosmo.herik.fronglos.dto.response.PostResponse;
import cosmo.herik.fronglos.dto.response.UserProfileResponse;
import cosmo.herik.fronglos.service.PostService;
import cosmo.herik.fronglos.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final PostService postService;

    public UserController(UserService userService, PostService postService) {
        this.userService = userService;
        this.postService = postService;
    }

    @GetMapping("/me")
    public AuthUserProfileResponse getCurrentUser() {
        return userService.getAuthUserProfile();
    }

    @GetMapping("/{id}")
    public UserProfileResponse getUserById(@PathVariable UUID id) {
        return userService.getUserProfile(id);
    }

    @GetMapping("/{id}/posts")
    public Page<PostResponse> getPostsFromUserId(
            @PathVariable UUID id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return postService.getPostsFromUserId(id, page, size);
    }
}
