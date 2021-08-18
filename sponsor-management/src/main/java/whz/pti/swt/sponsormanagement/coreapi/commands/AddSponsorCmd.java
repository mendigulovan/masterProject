package whz.pti.swt.sponsormanagement.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorStatus;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddSponsorCmd {
    @TargetAggregateIdentifier
    private String sponsorId;
    private String firmName;
    private SponsorStatus status;
}
