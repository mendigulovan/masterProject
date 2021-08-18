package whz.pti.swt.participantmanagement.organizer.queries;

import org.springframework.data.jpa.repository.JpaRepository;
import whz.pti.swt.participantmanagement.organizer.aggregate.EventEntity;


public interface EventRepository extends JpaRepository<EventEntity, String> {
}
