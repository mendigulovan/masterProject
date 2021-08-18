package whz.pti.swt.participantmanagement.participant.aggregate;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.modelling.saga.EndSaga;
import org.axonframework.modelling.saga.SagaEventHandler;
import org.axonframework.modelling.saga.StartSaga;
import org.axonframework.spring.stereotype.Saga;
import org.springframework.beans.factory.annotation.Autowired;
import whz.pti.swt.participantmanagement.coreapi.commands.*;
import whz.pti.swt.participantmanagement.coreapi.events.*;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.FoodWishes;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.Technologie;
import whz.pti.swt.participantmanagement.participant.aggregate.partEnum.TshirtSize;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static org.axonframework.modelling.saga.SagaLifecycle.associateWith;

@Saga
public class ParticipantManagementForEventSaga {

    @Autowired
    private transient CommandGateway commandGateway;

    private String participantId;
    private String participantDataId;
    private String eventId;
    private String description;
    private LocalDate eventsDate;
    private LocalDate registrationDeadline;
    private String participantName;
    private LocalDate participantsRegistrationTime;


    @StartSaga
    @SagaEventHandler(associationProperty = "participantId")
    public void on(ParticipantAddedEvt evt) {
        participantId = evt.getParticipantId();
        participantName = evt.getName();
        eventId = UUID.randomUUID().toString();
        description = "Hackathon Event 2021";
        eventsDate = LocalDate.of(2021, 10, 20);
        registrationDeadline = LocalDate.of(2020, 6, 30);
        associateWith("eventId", eventId);
        commandGateway.send(new CreateEventCmd(eventId, description, eventsDate, registrationDeadline));
        System.out.println("Start SAGA  Participant ::::::  "+participantName+" ::::::Added");
    }

    @SagaEventHandler(associationProperty = "eventId")
    public void on(EventCreatedEvt evt) {
        commandGateway.send(new ParticipantReceiveEmailCmd(participantId, evt.getEventId()));
        System.out.println("Saga::  Event Created "+description+"");

    }

    @SagaEventHandler(associationProperty = "participantId")
    public void on(ParticipantReceivedEmailEvt evt) {
        participantDataId = UUID.randomUUID().toString();
        participantsRegistrationTime = getRegistrationTimeOfParticipant();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: ");
        commandGateway.send(new FillDataOfParticipantCmd(evt.getParticipantId(), participantDataId, eventId, participantName,
                FoodWishes.values()[new Random().nextInt(FoodWishes.values().length)],
                Technologie.values()[new Random().nextInt(Technologie.values().length)],
                TshirtSize.values()[new Random().nextInt(TshirtSize.values().length)], participantsRegistrationTime));
        System.out.println("Saga:: "+ participantName+" Participant Received Email ");

    }

    private LocalDate getRegistrationTimeOfParticipant() {
        List<LocalDate> timeStamps=new ArrayList<>();
        timeStamps.add(LocalDate.of(2020, 7, 30));
        timeStamps.add(LocalDate.of(2020, 5, 30));
        timeStamps.add(LocalDate.of(2020, 6, 24));
        timeStamps.add( LocalDate.now());
        return timeStamps.get(new Random().nextInt(timeStamps.size()));
    }

    @SagaEventHandler(associationProperty = "participantId")
    public void on(ParticipantFilledDataEvt evt) {

        commandGateway.send(new GetParticipantDataCmd(eventId, participantId, participantDataId, evt.getName(), evt.getRegistrationTime()));
        System.out.println("Saga:: .......Participant filled Data to participate in the Hackathon Event...........");
    }

    @SagaEventHandler(associationProperty = "participantId")
    public void on(ParticipantDataGotEvt evt) {
        commandGateway.send(new SendResponseToParticipantCmd(evt.getEventId(), evt.getParticipantId(), ""));
        System.out.println("Saga:: .......Participant got Response from the Hackathon Event..........."+participantName);
        System.out.println(participantName+"Deadline: "+registrationDeadline+" Registration Date:"+participantsRegistrationTime);
    }

    @EndSaga
    @SagaEventHandler(associationProperty = "participantId")
    public void on(ResponseToParticipantSentEvt evt) {
        System.out.println("Saga:: .......Participant "+participantName+ " response Status: "+evt.getResponseStatus());
        System.out.println("::::::::::::::::::::::::::::Saga Ended:::::::::::::::::::::::::::::::::::");
    }
}
