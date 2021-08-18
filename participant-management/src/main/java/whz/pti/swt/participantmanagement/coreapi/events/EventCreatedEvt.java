package whz.pti.swt.participantmanagement.coreapi.events;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EventCreatedEvt {
    private final String eventId;
    private final String description;
    private final LocalDate eventsDate;
    private final LocalDate registrationDeadline;
}
