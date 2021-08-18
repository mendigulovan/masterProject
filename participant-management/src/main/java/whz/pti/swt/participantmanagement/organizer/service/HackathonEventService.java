package whz.pti.swt.participantmanagement.organizer.service;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whz.pti.swt.participantmanagement.coreapi.commands.CreateEventCmd;
import whz.pti.swt.participantmanagement.coreapi.commands.GetParticipantDataCmd;
import whz.pti.swt.participantmanagement.organizer.aggregate.EventEntity;
import whz.pti.swt.participantmanagement.organizer.queries.FindAllEventsQuery;
import whz.pti.swt.participantmanagement.organizer.queries.FindEventQuery;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataEntity;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantEntity;
import whz.pti.swt.participantmanagement.participant.queries.FindAllParticipantQuery;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HackathonEventService {

    private final CommandGateway gateway;
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public CompletableFuture<EventEntity> createHackathonEvent(EventEntity dto) {
        return this.gateway.send(new CreateEventCmd(UUID.randomUUID().toString(), dto.getDescription(), dto.getEventsDate(), dto.getRegistrationDeadline()));
    }

    public CompletableFuture<EventEntity> findById(String eventId) {
        return this.queryGateway.query(
                new FindEventQuery(eventId),
                ResponseTypes.instanceOf(EventEntity.class)
        );
    }

    public List<Object> getAllEventsdOfHackthon(String eventId) {
        return this.eventStore
                .readEvents(UUID.fromString(eventId).toString())
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }

    public CompletableFuture<ParticipantDataEntity> addParticipantToEvent(String eventId, ParticipantDataEntity dto) {
        return this.gateway.send(new GetParticipantDataCmd(eventId, dto.getParticipantId(), dto.getParticipantDataId(), dto.getName(), dto.getRegistrationTime()));
    }

    public List<EventEntity> findAllEvents() {
        return queryGateway.query(
                new FindAllEventsQuery(),
                ResponseTypes.multipleInstancesOf(EventEntity.class)).join();
    }
}
