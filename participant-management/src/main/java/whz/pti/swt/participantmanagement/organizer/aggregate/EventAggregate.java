package whz.pti.swt.participantmanagement.organizer.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import whz.pti.swt.participantmanagement.coreapi.commands.CreateEventCmd;
import whz.pti.swt.participantmanagement.coreapi.commands.GetParticipantDataCmd;
import whz.pti.swt.participantmanagement.coreapi.commands.SendResponseToParticipantCmd;
import whz.pti.swt.participantmanagement.coreapi.events.EventCreatedEvt;
import whz.pti.swt.participantmanagement.coreapi.events.ParticipantDataGotEvt;
import whz.pti.swt.participantmanagement.coreapi.events.ResponseToParticipantSentEvt;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor
@Aggregate
@Getter
@AllArgsConstructor
public class EventAggregate {
    @AggregateIdentifier
    private String eventId;
    private String description;
    private LocalDate eventsDate;
    private LocalDate registrationDeadline;
    private String responseStatus;
    @AggregateMember
    private Map<String, String> participantsOfEvent;

    @CommandHandler
    public EventAggregate(CreateEventCmd cmd) {
        AggregateLifecycle.apply(new EventCreatedEvt(cmd.getEventId(), cmd.getDescription(), cmd.getEventsDate(), cmd.getRegistrationDeadline()));
    }

    @EventSourcingHandler
    public void on(EventCreatedEvt evt) {
        this.eventId = evt.getEventId();
        this.description = evt.getDescription();
        this.eventsDate = evt.getEventsDate();
        this.registrationDeadline = evt.getRegistrationDeadline();
        participantsOfEvent = new HashMap<>();
    }

    @CommandHandler
    public void handle(GetParticipantDataCmd cmd) {
        AggregateLifecycle.apply(new ParticipantDataGotEvt(cmd.getEventId(), cmd.getParticipantId(), cmd.getParticipantDataId(), cmd.getName(), cmd.getRegistrationTime()
        ));
    }

    @EventSourcingHandler
    public void on(ParticipantDataGotEvt evt) {
        if (this.registrationDeadline.compareTo(evt.getRegistrationTime()) < 0) {
            System.out.println("Participant can't participate in Hackathon Event" +
                    "Registration Deadline is over");
            this.responseStatus = evt.getRegistrationTime()+" ::Deadline is Over";
        } else {
            this.participantsOfEvent.put(evt.getParticipantDataId(), evt.getName());
            System.out.println("Participant can participate in Hackathon Event");
            this.responseStatus = evt.getRegistrationTime()+" ::Registered";
        }
    }

    @CommandHandler
    public void handle(SendResponseToParticipantCmd cmd) {
        AggregateLifecycle.apply(new ResponseToParticipantSentEvt(cmd.getEventId(), cmd.getParticipantId(), this.responseStatus
        ));
    }

    @EventSourcingHandler
    public void on(ResponseToParticipantSentEvt evt) {
        System.out.println("Response Sent to a Participant " + evt.getParticipantId() + " " + evt.getResponseStatus());
    }

}
