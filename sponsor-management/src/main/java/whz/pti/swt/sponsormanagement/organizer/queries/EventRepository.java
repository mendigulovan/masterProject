package whz.pti.swt.sponsormanagement.organizer.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventEntity;


public interface EventRepository extends JpaRepository<EventEntity, String> {
}
