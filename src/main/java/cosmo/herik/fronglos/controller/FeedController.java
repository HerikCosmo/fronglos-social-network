package cosmo.herik.fronglos.controller;

import cosmo.herik.fronglos.dto.response.PostResponse;
import cosmo.herik.fronglos.service.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feed")
@SecurityRequirement(name = "bearerAuth")
public class FeedController {
    private final PostService postService;

    public FeedController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public Page<PostResponse> getFeed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return postService.getFeedFromCurrentId(page, size);
    }
}
