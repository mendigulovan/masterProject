package whz.pti.swt.sponsormanagement.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeclineSponsorRequestCmd {
    @TargetAggregateIdentifier
    public String eventId;
    public String sponsorId;
    public String packageId;
}
