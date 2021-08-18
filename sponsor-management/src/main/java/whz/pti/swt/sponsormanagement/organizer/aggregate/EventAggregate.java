package whz.pti.swt.sponsormanagement.organizer.aggregate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import whz.pti.swt.sponsormanagement.coreapi.commands.CreateEventCmd;
import whz.pti.swt.sponsormanagement.coreapi.commands.ResponseSponsorRequestCmd;
import whz.pti.swt.sponsormanagement.coreapi.commands.SponsorRequestCmd;
import whz.pti.swt.sponsormanagement.coreapi.events.EventCreatedEvt;
import whz.pti.swt.sponsormanagement.coreapi.events.SponsorRequestRespondEvt;
import whz.pti.swt.sponsormanagement.coreapi.events.SponsorRequestedEvt;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorStatus;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Aggregate
@Getter
@AllArgsConstructor
public class EventAggregate {
    @AggregateIdentifier
    private String eventId;
    private String description;
    @AggregateMember
    private Set<EventPackageEntity> eventPackage;
    @AggregateMember
    private Set<String> sponsorsOfEvent;

    @CommandHandler
    public EventAggregate(CreateEventCmd cmd) {
        AggregateLifecycle.apply(new EventCreatedEvt(cmd.getEventId(), cmd.getDescription()));
    }

    @EventSourcingHandler
    public void on(EventCreatedEvt evt) {
        this.eventId = evt.getEventId();
        this.description = evt.getDescription();
        this.eventPackage = new HashSet<>();
        this.sponsorsOfEvent = new HashSet<>();
        eventPackage.add(new EventPackageEntity("", "P1", SponsorStatus.VIP));

    }

    @CommandHandler
    public void handle(SponsorRequestCmd cmd) {
        AggregateLifecycle.apply(new SponsorRequestedEvt(cmd.getEventId(), cmd.getSponsorId(), cmd.getSponsorStatus()));
    }

    @EventSourcingHandler
    public void on(SponsorRequestedEvt evt) {
        if (evt.getSponsorStatus().equals(eventPackage.stream().findFirst().get().getStatus()))
            sponsorsOfEvent.add(evt.getSponsorId());
    }

    @CommandHandler
    public void handle(ResponseSponsorRequestCmd cmd) {
        String responseStatus = "";

        if (sponsorsOfEvent.contains(cmd.getSponsorId())) responseStatus = "Accepted";
        else responseStatus = "Rejected";

        AggregateLifecycle.apply(new SponsorRequestRespondEvt(cmd.getEventId(), cmd.getSponsorId(), responseStatus));
    }

    @EventSourcingHandler
    public void on(SponsorRequestRespondEvt evt) {
        System.out.println("Package Chosen event: " + evt.getEventId() + " " + evt.getSponsorId());

    }


}
