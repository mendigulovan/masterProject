package whz.pti.swt.sponsormanagement.organizer.queries;


import org.springframework.data.jpa.repository.JpaRepository;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventPackageEntity;


public interface EventPackageRepository extends JpaRepository<EventPackageEntity, String> {

}
