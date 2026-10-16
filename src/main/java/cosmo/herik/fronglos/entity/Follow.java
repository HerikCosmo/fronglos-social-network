package cosmo.herik.fronglos.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(
    name = "follows",
    uniqueConstraints = {
            @UniqueConstraint(
                    name = "uk_follow_follower_following",
                    columnNames = {"follower_id", "following_id"}
            )
    }
)
public class Follow {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "follower_id", nullable = false)
    private User follower;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "following_id", nullable = false)
    private User following;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Instant followedAt;

    public Follow(User follower, User following) {
        this.follower = follower;
        this.following = following;
    }
}
