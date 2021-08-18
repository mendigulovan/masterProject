package whz.pti.swt.sponsormanagement.organizer.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventEntity;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventPackageEntity;
import whz.pti.swt.sponsormanagement.organizer.service.EventCommandService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/hackathonEvents/event")
@Api(value = "Hackathon Event Queries")
@AllArgsConstructor
public class EventQueryController {
    private final EventCommandService eventQueryService;

    @GetMapping("/{eventId}")
    public CompletableFuture<EventEntity> findById(@PathVariable("eventId") String eventId) {
        return this.eventQueryService.findById(eventId);
    }

    @GetMapping("/{eventId}/package/{packageId}")
    public CompletableFuture<EventPackageEntity> findPackageById(@PathVariable("packageId") String packageId) {
        return this.eventQueryService.findPackageById(packageId);
    }

    @GetMapping("/{eventId}/package")
    public List<EventPackageEntity> getAllPackages(@PathVariable(value = "eventId") String eventId) {
        List<EventPackageEntity> list = new ArrayList<>();
        return this.eventQueryService.findAllPackagesOfEvent(eventId, list);
    }

    @GetMapping
    public List<EventEntity> getAllEvents() {
        return this.eventQueryService.findAllEvents();
    }


}
