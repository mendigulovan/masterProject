package whz.pti.swt.sponsormanagement.organizer.aggregate;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import whz.pti.swt.sponsormanagement.coreapi.commands.*;
import whz.pti.swt.sponsormanagement.coreapi.events.*;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorStatus;

import javax.persistence.Transient;
import java.util.UUID;

import static org.axonframework.modelling.saga.SagaLifecycle.associateWith;

@Saga
public class EventManagementForSponsorSaga {
    @Transient
    @Autowired
    private transient CommandGateway commandGateway;
    private String eventId;
    private String sponsorId;
    private String responseStatus;
    private String firmName;
    private String description;
    private SponsorStatus status;

    @StartSaga
    @SagaEventHandler(associationProperty = "sponsorId")
    public void on(SponsorAddedEvt evt) {
        sponsorId = evt.getSponsorId();
        firmName = evt.getFirmName();
        status = evt.getStatus();
        eventId = UUID.randomUUID().toString();
        description = "Hackathon 2020";
        associateWith("eventId ", eventId);
        commandGateway.send(new CreateEventCmd(eventId, description));
        System.out.println("Start SAGA  Sponsor ::::::  "+firmName+" ::::::Added");
    }

    @SagaEventHandler(associationProperty = "eventId")
    public void on(EventCreatedEvt evt) {
        eventId = evt.getEventId();
        System.out.println("sponsorId:::: " + sponsorId + "  eventId  " + eventId);
        commandGateway.send(new SponsorReceiveEmailCmd(sponsorId, eventId));
        System.out.println("Saga::  Event Created "+description);
    }

    @SagaEventHandler(associationProperty = "sponsorId")
    public void on(SponsorReceivedEmailEvt evt) {
        commandGateway.send(new ApplySponsorForEventCmd(evt.getSponsorId(), evt.getEventId(), firmName));
        this.responseStatus = "Accepted";
        System.out.println("Saga:: "+ firmName+" Sponsor Received Email ");
    }

    @SagaEventHandler(associationProperty = "sponsorId")
    public void on(SponsorAppliedEvt evt) {
        commandGateway.send(new SponsorRequestCmd(evt.getEventId(), evt.getSponsorId(), status));
        System.out.println("Saga:: "+firmName+"  Sponsor Applied for Event::::"+description);
    }

    @SagaEventHandler(associationProperty = "eventId")
    public void on(SponsorRequestedEvt evt) {
        commandGateway.send(new ResponseSponsorRequestCmd(evt.getEventId(), evt.getSponsorId(), ""));
        System.out.println("Saga:: "+description+" Event Aggregate received a Request from Sponsor: "+firmName);
    }

    @SagaEventHandler(associationProperty = "eventId")
    public void on(SponsorRequestRespondEvt evt) {
        if (evt.getResponseStatus().equals("Accepted")) {
            commandGateway.send(new GetSponsorConfirmationCmd(evt.getSponsorId(), evt.getResponseStatus()));
            System.out.println("Saga:: Respond for Sponsors Request:::: " + evt.getResponseStatus());
        } else {
            commandGateway.send(new GetSponsorRefusalCmd(evt.getSponsorId()));
            System.out.println("Saga:: Respond for Sponsors Request:::: Rejected");
        }
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "sponsorId")
    public void on(SponsorGotConfirmationEvt evt) {
        System.out.println("Saga::::::::::Ended::::::: "+firmName+" is Accepted As Sponsor");
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "sponsorId")
    public void on(SponsorGotRefusalEvt evt) {
        System.out.println("Saga::::::::::Ended::::::: "+firmName+" is Rejected As Sponsor");
    }
}