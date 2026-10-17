package cosmo.herik.fronglos.controller;

import cosmo.herik.fronglos.docs.FeedControllerDocs;
import cosmo.herik.fronglos.dto.response.PostResponse;
import cosmo.herik.fronglos.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feed")
public class FeedController implements FeedControllerDocs {
    private final PostService postService;

    public FeedController(PostService postService) {
        this.postService = postService;
    }

    @Override
    @GetMapping
    public Page<PostResponse> getFeed(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size
    ) {
        return postService.getFeedFromCurrentId(page, size);
    }
}
