package whz.pti.swt.participantmanagement.coreapi.events;

import lombok.Data;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.FoodWishes;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.Technologie;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.TshirtSize;

import java.time.LocalDate;

@Data
public class ParticipantFilledDataEvt {

    private final String participantId;
    private final String participantDataId;
    private final String eventId;
    private final String name;
    private final FoodWishes foodWishes;
    private final Technologie technologie;
    private final TshirtSize tShirtSize;
    private final LocalDate registrationTime;
}
