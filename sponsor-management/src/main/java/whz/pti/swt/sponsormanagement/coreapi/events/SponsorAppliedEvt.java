package whz.pti.swt.sponsormanagement.coreapi.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
public class SponsorAppliedEvt {
private final String sponsorId;
private final String eventId;
private final String firmName;



}
