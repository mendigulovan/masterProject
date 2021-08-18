package whz.pti.swt.participantmanagement.organizer.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import whz.pti.swt.participantmanagement.organizer.aggregate.EventEntity;
import whz.pti.swt.participantmanagement.organizer.service.HackathonEventService;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataEntity;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping(value = "/hackathonEvents/event")
@Api(value = "Hackathon Event Commands")
@AllArgsConstructor
public class HackathonEventController {

    private final HackathonEventService hackathonEventService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<EventEntity> createNewEvent(@RequestBody EventEntity dto) {
        return this.hackathonEventService.createHackathonEvent(dto);

    }

    @PostMapping("/{eventId}/participantDatas")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<ParticipantDataEntity> addParticipantToEvent(@PathVariable(value = "eventId") String eventId, @RequestBody ParticipantDataEntity dto) throws ExecutionException, InterruptedException {
        return this.hackathonEventService.addParticipantToEvent(eventId, dto);

    }

}
