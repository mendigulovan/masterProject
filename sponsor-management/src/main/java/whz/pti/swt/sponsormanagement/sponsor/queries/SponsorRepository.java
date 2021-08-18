
package whz.pti.swt.sponsormanagement.sponsor.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorEntity;

import java.util.Optional;

public interface SponsorRepository extends JpaRepository<SponsorEntity, String> {
    Optional<SponsorEntity> findById(String id);

}

