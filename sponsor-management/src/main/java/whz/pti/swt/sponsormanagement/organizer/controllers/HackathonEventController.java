package whz.pti.swt.sponsormanagement.organizer.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventEntity;
import whz.pti.swt.sponsormanagement.organizer.aggregate.SponsorChosenPackageEntity;
import whz.pti.swt.sponsormanagement.organizer.service.EventCommandService;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorEntity;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@RestController
@RequestMapping(value = "/hackathonEvents/event")
@Api(value = "Hackathon Event Commands")
@AllArgsConstructor
public class HackathonEventController {

    private final EventCommandService eventCommandService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<EventEntity> createNewEvent(@Valid @RequestBody EventEntity dto) {
        return this.eventCommandService.createHackathonEvent(dto);

    }

    @PostMapping("/{eventId}/sponsorsPackage")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<SponsorChosenPackageEntity> addSponsorsPackageToEvent(@PathVariable(value = "eventId") String eventId, SponsorEntity dto) {
        return this.eventCommandService.sponsorRequest(eventId, dto);

    }

    @PutMapping("/{eventId}")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<SponsorChosenPackageEntity> approveSponsorRequest(@PathVariable(value = "eventId") String eventId, String sponsorId, String packageId) throws ExecutionException, InterruptedException {
        if (packageId.equals("") || packageId.isEmpty() || packageId.equals(null)) {
            return this.eventCommandService.sponsorRequestDecline(eventId, sponsorId, packageId);
        } else return this.eventCommandService.sponsorRequestApprove(eventId, sponsorId, packageId);
    }

}
