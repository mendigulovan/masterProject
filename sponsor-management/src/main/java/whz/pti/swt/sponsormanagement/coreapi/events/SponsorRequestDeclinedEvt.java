package whz.pti.swt.sponsormanagement.coreapi.events;

import lombok.Data;

@Data
public class SponsorRequestDeclinedEvt {
    public final String eventId;
    public final String sponsorId;
}
