package whz.pti.swt.sponsormanagement.coreapi.commands;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class SponsorReceiveEmailCmd {
    @TargetAggregateIdentifier
    String sponsorId;
    String eventId;
}
