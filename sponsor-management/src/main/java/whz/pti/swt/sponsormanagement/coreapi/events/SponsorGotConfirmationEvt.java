package whz.pti.swt.sponsormanagement.coreapi.events;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SponsorGotConfirmationEvt {
    private String sponsorId;
    private String eventId;
    private String responseStatus;
}
