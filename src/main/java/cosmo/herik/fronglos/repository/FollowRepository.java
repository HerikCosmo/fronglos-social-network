package cosmo.herik.fronglos.repository;

import cosmo.herik.fronglos.entity.Follow;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FollowRepository extends JpaRepository<Follow, UUID> {
    boolean existsByFollowerIdAndFollowingId(UUID followerId, UUID followingId);
    void deleteByFollowerIdAndFollowingId(UUID followerId, UUID followingId);
    long countByFollowerId(UUID followerId);
    long countByFollowingId(UUID followingId);
    Page<Follow> findByFollowerId(UUID followerId, Pageable pageable);
}
