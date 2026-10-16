package cosmo.herik.fronglos.service;

import cosmo.herik.fronglos.dto.request.PostRequest;
import cosmo.herik.fronglos.dto.response.PostResponse;
import cosmo.herik.fronglos.entity.Post;
import cosmo.herik.fronglos.exception.PostNotFoundException;
import cosmo.herik.fronglos.mapper.PostMapper;
import cosmo.herik.fronglos.repository.PostRepository;
import cosmo.herik.fronglos.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final CurrentUserService currentUserService;
    private final UserRepository userRepository;
    private final PostMapper postMapper = PostMapper.INSTANCE;

    public PostService(
            PostRepository postRepository,
            CurrentUserService currentUserService,
            UserRepository userRepository
    ) {
        this.postRepository = postRepository;
        this.currentUserService = currentUserService;
        this.userRepository = userRepository;
    }

    public PostResponse save(PostRequest request) {
        var user = currentUserService.getCurrentUser();

        var post = Post.builder()
                .content(request.content())
                .user(user)
                .build();

        return postMapper.toPostResponse(postRepository.save(post));
    }

    public PostResponse getById(UUID id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new PostNotFoundException(id.toString()));

        return postMapper.toPostResponse(post);
    }

    public Page<PostResponse> getPostsFromUserId(UUID id, int page, int size) {
        userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(id.toString()));

        Pageable pageable = PageRequest.of(
                page,
                size,
                Sort.by("createdAt").descending()
        );

        Page<Post> posts = postRepository.findAllByUserId(id, pageable);

        return posts.map(postMapper::toPostResponse);
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
