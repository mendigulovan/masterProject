package whz.pti.swt.participantmanagement.coreapi.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.FoodWishes;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.Technologie;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.TshirtSize;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FillDataOfParticipantCmd {
    @TargetAggregateIdentifier
    private String participantId;
    private String participantDataId;
    private String eventId;
    private String name;
    private FoodWishes foodWishes;
    private Technologie technologie;
    private TshirtSize tShirtSize;
    private LocalDate registrationTime;
}
