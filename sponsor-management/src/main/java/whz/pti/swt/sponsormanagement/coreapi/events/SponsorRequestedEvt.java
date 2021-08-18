package whz.pti.swt.sponsormanagement.coreapi.events;

import lombok.Data;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorStatus;

@Data
public class SponsorRequestedEvt {
    private final String eventId;
    private final String sponsorId;
    private final SponsorStatus sponsorStatus;


}
