
package whz.pti.swt.sponsormanagement.sponsor.queries;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import whz.pti.swt.sponsormanagement.coreapi.events.*;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorEntity;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class SponsorProjection {

    private final SponsorRepository sponsorRepository;

    @EventHandler
    public void on(SponsorAddedEvt evt) {
        SponsorEntity entity = new SponsorEntity(
                evt.getSponsorId(), "", "", "", evt.getFirmName(), evt.getStatus());

        this.sponsorRepository.save(entity);
    }


    @EventHandler
    public void on(SponsorReceivedEmailEvt evt) {
        Optional<SponsorEntity> spon = this.sponsorRepository.findById(evt.getSponsorId());
        if (spon.equals(null)) {
            return;
        }
        spon.get().setEmailStatus("Received");
        spon.get().setEventId(evt.getEventId());
        this.sponsorRepository.save(spon.get());
    }

    @EventHandler
    public void on(SponsorAppliedEvt evt) {
        Optional<SponsorEntity> spon = this.sponsorRepository.findById(evt.getSponsorId());
        if (spon.equals(null)) {
            return;
        }
        spon.get().setEventId(evt.getEventId());
        this.sponsorRepository.save(spon.get());
    }

    @EventHandler
    public void on(SponsorGotConfirmationEvt evt) {
        Optional<SponsorEntity> spon = this.sponsorRepository.findById(evt.getSponsorId());
        if (spon.equals(null)) {
            return;
        }
        spon.get().setResponseStatus("Accepted");
        // spon.get().setPackageId(evt.getPackageId());
        this.sponsorRepository.save(spon.get());
    }

    @EventHandler
    public void on(SponsorGotRefusalEvt evt) {
        Optional<SponsorEntity> spon = this.sponsorRepository.findById(evt.getSponsorId());
        if (spon.equals(null)) {
            return;
        }
        spon.get().setResponseStatus("Rejected");
        spon.get().setEventId("");
        this.sponsorRepository.save(spon.get());
    }

    @QueryHandler
    public SponsorEntity handle(FindSponsorQuery query) {
        return this.sponsorRepository.findById(query.getId()).orElse(null);
    }

    @QueryHandler
    public List<SponsorEntity> handle(FindAllSponsorsQuery query) {
        return this.sponsorRepository.findAll();
    }

}

