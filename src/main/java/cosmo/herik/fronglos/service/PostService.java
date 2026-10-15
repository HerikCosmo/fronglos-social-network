package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.dto.request.PostRequest;
import cosmo.herik.fronglos.dto.response.PostResponse;
import cosmo.herik.fronglos.entity.Post;
import cosmo.herik.fronglos.exception.PostNotFoundException;
import cosmo.herik.fronglos.repository.PostRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public PostResponse getById(UUID id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));

        return new PostResponse(
                post.getId(),
                post.getContent(),
                post.getCreatedAt(),
                new PostResponse.AuthorResponse(
                        post.getUser().getId(),
                        post.getUser().getDisplayName(),
                        post.getUser().getPublicUsername()
                )
        );
    }

    public void deleteById(UUID id) {
        var user = currentUserService.getCurrentUser();

        var post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));

        if(!user.getId().equals(post.getUser().getId())) {
            throw new AccessDeniedException("You are not allowed to delete this post");
        }

        postRepository.deleteById(id);
    }
}
