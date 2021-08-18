package whz.pti.swt.participantmanagement.participant.queries;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import whz.pti.swt.participantmanagement.coreapi.events.ParticipantAddedEvt;
import whz.pti.swt.participantmanagement.coreapi.events.ParticipantFilledDataEvt;
import whz.pti.swt.participantmanagement.coreapi.events.ParticipantReceivedEmailEvt;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataEntity;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataRepository;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantEntity;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantRepository;

import java.util.Optional;


@RequiredArgsConstructor
@Component
public class ParticipantProjection {
    private final ParticipantRepository participantRepository;

    private final ParticipantDataRepository participantDataRepository;

    @EventHandler
    public void on(ParticipantAddedEvt event) {
        ParticipantEntity participantEntity = new ParticipantEntity(
                event.getParticipantId(), "",
                event.getName()
        );
        this.participantRepository.save(participantEntity);
    }

    @EventHandler
    public void on(ParticipantReceivedEmailEvt event) {
        Optional<ParticipantEntity> part = this.participantRepository.findById(event.getParticipantId());
        if (part.equals(null)) {
            return;
        }
        part.get().setEmailStatus("Received");
        this.participantRepository.save(part.get());
    }

    @EventHandler
    public void on(ParticipantFilledDataEvt event) {
        ParticipantDataEntity dataEntity = new ParticipantDataEntity(event.getParticipantDataId(), event.getParticipantId(), event.getEventId(),
                event.getName(), event.getTechnologie(), event.getFoodWishes(), event.getTShirtSize(), event.getRegistrationTime(), event.getEventId());
        Optional<ParticipantEntity> part = this.participantRepository.findById(event.getParticipantId());
        if (part.equals(null)) {
            return;
        }
        if (part.get().getEmailStatus().equals("Received"))
            participantDataRepository.save(dataEntity);
    }

    @QueryHandler
    public ParticipantEntity handle(FindParticipantQuery query) {
        return this.participantRepository.findById(query.getId()).orElse(null);
    }

    @QueryHandler
    public ParticipantDataEntity handle(FindParticipantDataQuery query) {
        return this.participantDataRepository.findById(query.getParticipantDataId()).orElse(null);
    }

    @QueryHandler
    public Page<ParticipantEntity> handle(FindAllParticipantQuery query) {
        return this.participantRepository.findAll(query.getPage());
    }
}
