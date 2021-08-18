package whz.pti.swt.sponsormanagement.coreapi.events;

import lombok.Data;

@Data
public class SponsorRequestRespondEvt {
    private final String eventId;
    private final String sponsorId;
    private final String responseStatus;

}
