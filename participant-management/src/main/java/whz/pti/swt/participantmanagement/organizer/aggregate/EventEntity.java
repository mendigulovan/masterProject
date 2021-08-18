package whz.pti.swt.participantmanagement.organizer.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EventEntity {
    @Id
    private String eventId;
    private String description;
    private LocalDate eventsDate;
    private LocalDate registrationDeadline;
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<ParticipantDataEntity> participantsOfEvent;

}

