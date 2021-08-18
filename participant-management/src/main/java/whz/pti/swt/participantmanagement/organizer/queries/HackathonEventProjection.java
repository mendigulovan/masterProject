package whz.pti.swt.participantmanagement.organizer.queries;

import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;
import whz.pti.swt.participantmanagement.coreapi.events.EventCreatedEvt;
import whz.pti.swt.participantmanagement.coreapi.events.ParticipantDataGotEvt;
import whz.pti.swt.participantmanagement.coreapi.events.ResponseToParticipantSentEvt;
import whz.pti.swt.participantmanagement.organizer.aggregate.EventEntity;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataEntity;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataRepository;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Component
public class HackathonEventProjection {
    private final EventRepository eventRepository;

    private final ParticipantDataRepository participantDataRepository;


    @EventHandler
    @Transactional
    public void on(EventCreatedEvt evt) {
        EventEntity entity = new EventEntity(evt.getEventId(), evt.getDescription(), evt.getEventsDate(), evt.getRegistrationDeadline(), new HashSet<>());
        this.eventRepository.save(entity);
    }

    @EventHandler
    @Transactional
    public void on(ParticipantDataGotEvt evt) {
        EventEntity event = this.eventRepository.findById(evt.getEventId()).get();
        ParticipantDataEntity participantData = this.participantDataRepository.findById(evt.getParticipantDataId()).get();
        if (event.getRegistrationDeadline().compareTo(evt.getRegistrationTime()) < 0) {
            participantData.setResponseStatus("Deadline is Over");
        } else {
            participantData.setResponseStatus("Registered");
            Set<ParticipantDataEntity> set = event.getParticipantsOfEvent();
            set.add(participantData);
            event.setParticipantsOfEvent(set);
            this.eventRepository.save(event);
        }
        this.participantDataRepository.save(participantData);
    }

    @EventHandler
    @Transactional
    public void on(ResponseToParticipantSentEvt evt) {

    }

    @QueryHandler
    public EventEntity handle(FindEventQuery query) {
        return this.eventRepository.findById(query.getId()).orElse(null);
    }

    @QueryHandler
    public List<EventEntity> handle(FindAllEventsQuery query) {
        return this.eventRepository.findAll();
    }


}
