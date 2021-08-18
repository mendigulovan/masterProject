package whz.pti.swt.participantmanagement.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddParticipantCmd {
    @TargetAggregateIdentifier
    private String participantId;
    private String name;


}
