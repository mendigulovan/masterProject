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
public class SponsorChoosePackageCmd {
    @TargetAggregateIdentifier
    private String eventId;
    private String packageId;
    // private String eventPackage;
    private String sponsorId;
    // private String firmName;

}
