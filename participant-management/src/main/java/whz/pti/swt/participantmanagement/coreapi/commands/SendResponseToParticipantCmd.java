package whz.pti.swt.participantmanagement.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendResponseToParticipantCmd {
    @TargetAggregateIdentifier
    private String eventId;
    private String participantId;
    private String responseStatus;
}
