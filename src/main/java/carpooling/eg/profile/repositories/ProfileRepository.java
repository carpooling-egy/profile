package carpooling.eg.profile.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import carpooling.eg.profile.models.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {
}
