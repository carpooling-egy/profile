package carpooling.eg.profile.DAOs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import carpooling.eg.profile.Models.EntityClasses.Profile;
import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, String> {
    Optional<Profile> findByEmail(String email);
    Optional<Profile> findByUserId(String userId);
    boolean existsByEmail(String email);
    boolean existsByUserId(String userId);
    void deleteByEmail(String email);
}
