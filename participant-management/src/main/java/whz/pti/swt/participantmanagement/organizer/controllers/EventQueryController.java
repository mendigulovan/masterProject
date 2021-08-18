package whz.pti.swt.participantmanagement.organizer.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whz.pti.swt.participantmanagement.organizer.aggregate.EventEntity;
import whz.pti.swt.participantmanagement.organizer.service.HackathonEventService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/hackathonEvents/event")
@Api(value = "Hackathon Event Queries")
@AllArgsConstructor
public class EventQueryController {
    private final HackathonEventService eventQueryService;

    @GetMapping("/{eventId}")
    public CompletableFuture<EventEntity> findById(@PathVariable("eventId") String eventId) {
        return this.eventQueryService.findById(eventId);
    }


    @GetMapping
    public List<EventEntity> getAllEvents() {
        return this.eventQueryService.findAllEvents();
    }


}
