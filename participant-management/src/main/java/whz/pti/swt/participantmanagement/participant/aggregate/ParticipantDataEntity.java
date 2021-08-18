package whz.pti.swt.participantmanagement.participant.aggregate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.FoodWishes;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.Technologie;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.TshirtSize;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ParticipantDataEntity {

    @Id
    private String participantDataId;
    private String participantId;
    private String eventId;
    private String name;
    @Enumerated(EnumType.STRING)
    private Technologie technologie;
    @Enumerated(EnumType.STRING)
    private FoodWishes foodWishes;
    @Enumerated(EnumType.STRING)
    private TshirtSize tShirtSize;
    private LocalDate registrationTime;
    private String responseStatus;
}
