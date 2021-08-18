package whz.pti.swt.participantmanagement.coreapi.events;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ParticipantDataGotEvt {
    private final String eventId;
    private final String participantId;
    private final String participantDataId;
    private final String name;
    private final LocalDate registrationTime;
}
