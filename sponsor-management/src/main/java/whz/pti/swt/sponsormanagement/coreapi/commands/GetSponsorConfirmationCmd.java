package whz.pti.swt.sponsormanagement.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class GetSponsorConfirmationCmd {
    @TargetAggregateIdentifier
    private String sponsorId;
    private String responseStatus;
}
