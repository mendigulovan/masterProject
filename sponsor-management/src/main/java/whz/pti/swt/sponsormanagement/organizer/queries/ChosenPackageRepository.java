package whz.pti.swt.sponsormanagement.organizer.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.pti.swt.sponsormanagement.organizer.aggregate.SponsorChosenPackageEntity;


public interface ChosenPackageRepository extends JpaRepository<SponsorChosenPackageEntity, String> {
}
