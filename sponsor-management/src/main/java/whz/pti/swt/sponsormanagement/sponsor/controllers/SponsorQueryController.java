package whz.pti.swt.sponsormanagement.sponsor.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorEntity;
import whz.pti.swt.sponsormanagement.sponsor.services.SponsorService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/sponsors")
@Api(value = "Sponsor Event Queries")
@AllArgsConstructor
public class SponsorQueryController {

    private final SponsorService sponsorService;


    @GetMapping("/{sponsorId}")
    public CompletableFuture<SponsorEntity> findById(@PathVariable("sponsorId") String sponsorId) {
        return this.sponsorService.findById(sponsorId);
    }

    @GetMapping
    public List<SponsorEntity> getAllSponsors() {
        return this.sponsorService.findAllSponsors();
    }

}
