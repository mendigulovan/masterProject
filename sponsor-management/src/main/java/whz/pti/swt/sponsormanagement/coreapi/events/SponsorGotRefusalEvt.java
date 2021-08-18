package whz.pti.swt.sponsormanagement.coreapi.events;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SponsorGotRefusalEvt {

    private String sponsorId;
}
