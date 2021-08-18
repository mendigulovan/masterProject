package whz.pti.swt.sponsormanagement.coreapi.events;

import lombok.Data;

@Data
public class EventCreatedEvt {
    private final String eventId;
    private final String description;

}
