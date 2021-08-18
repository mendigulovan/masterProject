package whz.pti.swt.participantmanagement.coreapi.events;

import lombok.Data;

@Data
public class ResponseToParticipantSentEvt {

    private final String eventId;
    private final String participantId;
    private final String responseStatus;
}
