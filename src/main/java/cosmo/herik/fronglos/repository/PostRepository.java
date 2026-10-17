package cosmo.herik.fronglos.repository;

import cosmo.herik.fronglos.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    Page<Post> findAllByUserId(UUID userId, Pageable pageable);
    @Query("""
    select p from Post p
        where p.user.id = :userId
        or p.user.id in (
    	    select f.following.id  from Follow f
    	    where f.follower.id = :userId
        )
        order by p.createdAt desc
    """)
    Page<Post> findFeedByUserId(UUID userId, Pageable pageable);
}
