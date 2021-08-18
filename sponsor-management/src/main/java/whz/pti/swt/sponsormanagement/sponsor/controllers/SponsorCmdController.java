package whz.pti.swt.sponsormanagement.sponsor.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorEntity;
import whz.pti.swt.sponsormanagement.sponsor.services.SponsorService;


import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/sponsors")
@Api(value = "Sponsor Event Commands")
@AllArgsConstructor
public class SponsorCmdController {

    private final SponsorService sponsorService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<SponsorEntity> addSponsor(@RequestBody SponsorEntity dto) throws InterruptedException {
        return this.sponsorService.addSponsorToDb(dto);
    }

    @PutMapping(value = "/{sponsorId}")
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public CompletableFuture<SponsorEntity> updateSponsorStatus(@PathVariable(value = "sponsorId") String sponsorId,String eventId) {
        return this.sponsorService.updateEmailStatusSponsor(sponsorId,eventId);
    }

    @PatchMapping(value = "/{sponsorId}")
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public CompletableFuture<SponsorEntity> getConfirmationFromEvent(@PathVariable(value = "sponsorId") String sponsorId,String packageId) {
        if(packageId.equals("get")||packageId.isEmpty()) return this.sponsorService.getRefusal(sponsorId);
        else return this.sponsorService.getConfirmation(sponsorId,packageId);
    }








}
