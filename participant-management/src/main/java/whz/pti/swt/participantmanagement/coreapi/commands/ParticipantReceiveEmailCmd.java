package whz.pti.swt.participantmanagement.coreapi.commands;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ParticipantReceiveEmailCmd {
    @TargetAggregateIdentifier
    String participantId;
    String eventId;
}
