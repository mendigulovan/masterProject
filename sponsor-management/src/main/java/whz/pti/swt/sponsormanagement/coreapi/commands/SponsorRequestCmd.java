package whz.pti.swt.sponsormanagement.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SponsorRequestCmd {
    @TargetAggregateIdentifier
    private String eventId;
    private String sponsorId;
    private SponsorStatus sponsorStatus;


}
