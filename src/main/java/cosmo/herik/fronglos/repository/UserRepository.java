package cosmo.herik.fronglos.repository;

import cosmo.herik.fronglos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findUserByEmail(String username);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
