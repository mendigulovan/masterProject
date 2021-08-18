package whz.pti.swt.sponsormanagement.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApplySponsorForEventCmd {

    @TargetAggregateIdentifier
    private String sponsorId;
    private String eventId;
    private String firmName;
}
