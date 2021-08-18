package whz.pti.swt.sponsormanagement.sponsor.aggregate;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import whz.pti.swt.sponsormanagement.coreapi.commands.*;
import whz.pti.swt.sponsormanagement.coreapi.events.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
@Aggregate
public class SponsorAggregate {
    @AggregateIdentifier
    String sponsorId;
    String applicationStatus;
    @Enumerated(EnumType.STRING)
    SponsorStatus status;
    String emailStatus;
    String eventId;

    @CommandHandler
    public SponsorAggregate(AddSponsorCmd cmd) {
        AggregateLifecycle.apply(new SponsorAddedEvt(cmd.getSponsorId(), cmd.getFirmName(), cmd.getStatus()));
    }

    @EventSourcingHandler
    public void on(SponsorAddedEvt evt) {
        this.sponsorId = evt.getSponsorId();
        this.status = evt.getStatus();
    }

    @CommandHandler
    public void handle(SponsorReceiveEmailCmd cmd) {
        AggregateLifecycle.apply(new SponsorReceivedEmailEvt(cmd.getSponsorId(), cmd.getEventId()));
    }

    @EventSourcingHandler
    public void on(SponsorReceivedEmailEvt evt) {
        this.emailStatus = "Received";
        this.eventId = evt.getEventId();
    }

    @CommandHandler
    public void handle(ApplySponsorForEventCmd cmd) {
        AggregateLifecycle.apply(new SponsorAppliedEvt(cmd.getSponsorId(), cmd.getEventId(), cmd.getFirmName()));
    }

    @EventSourcingHandler
    public void on(SponsorAppliedEvt evt) {
    }

    @CommandHandler
    public void handle(GetSponsorConfirmationCmd cmd) {
        AggregateLifecycle.apply(new SponsorGotConfirmationEvt(cmd.getSponsorId(), eventId, cmd.getResponseStatus()));
    }

    @EventSourcingHandler
    public void on(SponsorGotConfirmationEvt evt) {
        this.applicationStatus = evt.getResponseStatus();
    }

    @CommandHandler
    public void handle(GetSponsorRefusalCmd cmd) {
        AggregateLifecycle.apply(new SponsorGotRefusalEvt(cmd.getSponsorId()));
    }

    @EventSourcingHandler
    public void on(SponsorGotRefusalEvt evt) {
        this.applicationStatus = "Refused";
    }


}
