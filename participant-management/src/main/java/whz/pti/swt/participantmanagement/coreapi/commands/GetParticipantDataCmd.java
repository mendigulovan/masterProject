package whz.pti.swt.participantmanagement.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetParticipantDataCmd {
    @TargetAggregateIdentifier
    private String eventId;
    private String participantId;
    private String participantDataId;
    private String name;
    private LocalDate registrationTime;
}
