package whz.pti.swt.participantmanagement.participant.aggregate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepository extends JpaRepository<ParticipantEntity, String> {

}
