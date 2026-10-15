package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.dto.request.PostRequest;
import cosmo.herik.fronglos.dto.response.PostResponse;
import cosmo.herik.fronglos.entity.Post;
import cosmo.herik.fronglos.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CurrentUserService currentUserService;

    public PostService(PostRepository postRepository, CurrentUserService currentUserService) {
        this.postRepository = postRepository;
        this.currentUserService = currentUserService;
    }

    public PostResponse save(PostRequest request) {
        var user = currentUserService.getCurrentUser();

        var post = Post.builder()
                .content(request.content())
                .user(user)
                .build();

        var savedPost = postRepository.save(post);

        return new PostResponse(
                savedPost.getId(),
                savedPost.getContent(),
                savedPost.getCreatedAt(),
                new PostResponse.AuthorResponse(
                        user.getId(),
                        user.getDisplayName(),
                        user.getPublicUsername()
                )
        );

    }
}
