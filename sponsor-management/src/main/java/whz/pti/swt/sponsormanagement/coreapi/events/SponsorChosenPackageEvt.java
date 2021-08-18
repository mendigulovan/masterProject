package whz.pti.swt.sponsormanagement.coreapi.events;

import lombok.Data;

@Data
public class SponsorChosenPackageEvt {
    private final String eventId;
    private final String eventPackageId;
    private final String sponsorId;

}
