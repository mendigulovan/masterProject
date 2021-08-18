package whz.pti.swt.participantmanagement.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreateEventCmd {
    @TargetAggregateIdentifier
    private String eventId;
    private String description;
    private LocalDate eventsDate;
    private LocalDate registrationDeadline;



}
