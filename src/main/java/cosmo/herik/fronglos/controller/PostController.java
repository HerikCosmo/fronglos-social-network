package cosmo.herik.fronglos.controller;

import cosmo.herik.fronglos.dto.request.PostRequest;
import cosmo.herik.fronglos.dto.response.PostResponse;
import cosmo.herik.fronglos.service.PostService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public PostResponse savePost(@Valid @RequestBody PostRequest request) {
        return postService.save(request);
    }
}
