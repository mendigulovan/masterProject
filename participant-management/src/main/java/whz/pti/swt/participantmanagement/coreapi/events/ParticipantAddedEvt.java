package whz.pti.swt.participantmanagement.coreapi.events;

import lombok.Data;

@Data
public class ParticipantAddedEvt {
    private final String participantId;
    private final String name;
}
