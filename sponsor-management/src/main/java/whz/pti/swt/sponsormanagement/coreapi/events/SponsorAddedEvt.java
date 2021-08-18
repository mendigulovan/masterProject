package whz.pti.swt.sponsormanagement.coreapi.events;

import lombok.Data;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorStatus;

@Data
public class SponsorAddedEvt {
    private final String sponsorId;
    private final String firmName;
    private final SponsorStatus status;
}
