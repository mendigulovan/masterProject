package whz.pti.swt.participantmanagement.participant.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import whz.pti.swt.participantmanagement.coreapi.commands.AddParticipantCmd;
import whz.pti.swt.participantmanagement.coreapi.commands.FillDataOfParticipantCmd;
import whz.pti.swt.participantmanagement.coreapi.commands.ParticipantReceiveEmailCmd;
import whz.pti.swt.participantmanagement.coreapi.events.ParticipantAddedEvt;
import whz.pti.swt.participantmanagement.coreapi.events.ParticipantFilledDataEvt;
import whz.pti.swt.participantmanagement.coreapi.events.ParticipantReceivedEmailEvt;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Aggregate
public class ParticipantAggregate {
    @AggregateIdentifier
    private String participantId;
    private String emailStatus;
    private String participantDataId;
    private String eventId;
    private String name;

    @CommandHandler
    public ParticipantAggregate(AddParticipantCmd command) {
        AggregateLifecycle.apply(
                new ParticipantAddedEvt(
                        command.getParticipantId(),
                        command.getName()
                ));
    }

    @EventSourcingHandler
    public void on(ParticipantAddedEvt event) {

        this.participantId = event.getParticipantId();
        this.emailStatus = "";
        this.participantDataId = "";
        this.eventId = "";
        this.name = event.getName();
    }

    @CommandHandler
    public void handle(ParticipantReceiveEmailCmd command) {
        AggregateLifecycle.apply(new ParticipantReceivedEmailEvt(command.getParticipantId()));
    }

    @EventSourcingHandler
    public void on(ParticipantReceivedEmailEvt event) {
        this.emailStatus = "Received";
    }

    @CommandHandler
    public void handle(FillDataOfParticipantCmd command) {
        AggregateLifecycle.apply(
                new ParticipantFilledDataEvt(
                        command.getParticipantId(), command.getParticipantDataId(), command.getEventId(), command.getName(),
                        command.getFoodWishes(),
                        command.getTechnologie(),
                        command.getTShirtSize(), command.getRegistrationTime()
                )
        );
    }

    @EventSourcingHandler
    public void on(ParticipantFilledDataEvt event) {
        if (emailStatus.equals("Received"))
            this.participantDataId = event.getParticipantDataId();
        else System.out.println("Did not receive any email");
    }
}
