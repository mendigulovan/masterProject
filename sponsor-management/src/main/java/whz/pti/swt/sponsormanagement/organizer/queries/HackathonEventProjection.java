package whz.pti.swt.sponsormanagement.organizer.queries;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import whz.pti.swt.sponsormanagement.coreapi.events.EventCreatedEvt;
import whz.pti.swt.sponsormanagement.coreapi.events.SponsorRequestDeclinedEvt;
import whz.pti.swt.sponsormanagement.coreapi.events.SponsorRequestRespondEvt;
import whz.pti.swt.sponsormanagement.coreapi.events.SponsorRequestedEvt;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventEntity;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventPackageEntity;
import whz.pti.swt.sponsormanagement.organizer.aggregate.SponsorChosenPackageEntity;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorEntity;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorStatus;
import whz.pti.swt.sponsormanagement.sponsor.queries.SponsorRepository;

import javax.transaction.Transactional;
import java.util.*;

@RequiredArgsConstructor
@Component
public class HackathonEventProjection {
    private final EventRepository eventRepository;
    private final EventPackageRepository eventPackageRepository;
    private final ChosenPackageRepository chosenPackageRepository;
    private final SponsorRepository sponsorRepository;


    @EventHandler
    @Transactional
    public void on(EventCreatedEvt evt) {
        Set<EventPackageEntity> packages = new HashSet<>();
        EventPackageEntity p1 = new EventPackageEntity(UUID.randomUUID().toString(), "P1", SponsorStatus.VIP);
        packages.add(p1);
        this.eventPackageRepository.save(p1);
        EventEntity entity = new EventEntity(evt.getEventId(), evt.getDescription(), packages, new HashSet<>());
        this.eventRepository.save(entity);
    }

    @EventHandler
    @Transactional
    public void on(SponsorRequestedEvt evt) {
        Optional<EventEntity> event = eventRepository.findById(evt.getEventId());
        if (event.equals(null)) {
            return;
        }
        Optional<SponsorEntity> sponsor = sponsorRepository.findById(evt.getSponsorId());
        if (sponsor.equals(null)) {
            return;
        }
        Set<EventPackageEntity> packageSet = event.get().getPackageList();
        EventPackageEntity[] strArray = packageSet.toArray(new EventPackageEntity[packageSet.size()]);
        EventPackageEntity evtPackage = strArray[0];
        if (evtPackage.getStatus().equals(sponsor.get().getStatus())) {
            SponsorChosenPackageEntity entity = new SponsorChosenPackageEntity(evt.getSponsorId(),
                    evtPackage.getPackageId(), sponsor.get().getFirmName(), evtPackage.getDescription());
            Set<SponsorChosenPackageEntity> dataList = event.get().getPackageChosenBySponsorList();
            dataList.add(entity);
            event.get().setPackageChosenBySponsorList(dataList);
            this.chosenPackageRepository.save(entity);
            this.eventRepository.save(event.get());
        }
    }


    @EventHandler
    @Transactional
    public void on(SponsorRequestRespondEvt evt) {
        EventEntity event = eventRepository.findById(evt.getEventId()).get();
        for (SponsorChosenPackageEntity entity : event.getPackageChosenBySponsorList()) {
            if (entity.getSponsorId().equals(evt.getSponsorId())) {
                SponsorEntity sponsor = sponsorRepository.findById(entity.getSponsorId()).get();
                sponsor.setResponseStatus("Accepted");
                sponsorRepository.save(sponsor);
            }
        }

    }

    @EventHandler
    @Transactional
    public void on(SponsorRequestDeclinedEvt evt) {
        EventEntity event = eventRepository.findById(evt.getEventId()).get();
        for (SponsorChosenPackageEntity entity : event.getPackageChosenBySponsorList()) {
            if (entity.getSponsorId().equals(evt.getSponsorId())) {
                SponsorEntity sponsor = sponsorRepository.findById(evt.getSponsorId()).get();
                sponsor.setResponseStatus("Rejected");
                sponsorRepository.save(sponsor);
            }
        }


    }

    @QueryHandler
    public EventEntity handle(FindEventQuery query) {
        return this.eventRepository.findById(query.getId()).orElse(null);
    }


    @QueryHandler
    public EventPackageEntity handle(FindPackageQuery query) {
        return this.eventPackageRepository.findById(query.getId()).orElse(null);
    }

    @QueryHandler
    public Set<EventPackageEntity> handle(FindAllPackages query) {
        return this.eventRepository.findById(query.id).get().getPackageList();
    }

    @QueryHandler
    public Set<SponsorChosenPackageEntity> handle(FindSponsorsAndPackagesOfEventQuery query) {
        return this.eventRepository.findById(query.getId()).get().getPackageChosenBySponsorList();
    }

    @QueryHandler
    public List<EventPackageEntity> handle(FindAllExistedPackages query) {
        return this.eventPackageRepository.findAll();
    }

    @QueryHandler
    public List<EventEntity> handle(FindAllEventsQuery query) {
        return this.eventRepository.findAll();
    }
}
