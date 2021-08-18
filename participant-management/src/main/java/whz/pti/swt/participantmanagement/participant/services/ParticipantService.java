package whz.pti.swt.participantmanagement.participant.services;

import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import whz.pti.swt.participantmanagement.coreapi.commands.AddParticipantCmd;
import whz.pti.swt.participantmanagement.coreapi.commands.FillDataOfParticipantCmd;
import whz.pti.swt.participantmanagement.coreapi.commands.ParticipantReceiveEmailCmd;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataEntity;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantEntity;
import whz.pti.swt.participantmanagement.participant.queries.FindAllParticipantQuery;
import whz.pti.swt.participantmanagement.participant.queries.FindParticipantDataQuery;
import whz.pti.swt.participantmanagement.participant.queries.FindParticipantQuery;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class ParticipantService {
    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public CompletableFuture<ParticipantEntity> addParticipant(ParticipantEntity dto) {

        return commandGateway.send(new AddParticipantCmd(
                UUID.randomUUID().toString(), dto.getName()
        ));
    }

    public CompletableFuture<ParticipantEntity> updateEmailSatusParticipant(String participantId, String eventId) {
        return commandGateway.send(new ParticipantReceiveEmailCmd(
                participantId, eventId
        ));
    }

    public CompletableFuture<ParticipantDataEntity> fillDataParticipant(String participantId, ParticipantDataEntity dto) {
        return commandGateway.send(
                new FillDataOfParticipantCmd(participantId, UUID.randomUUID().toString(), dto.getEventId(),
                        dto.getName(), dto.getFoodWishes(), dto.getTechnologie(),
                        dto.getTShirtSize(), dto.getRegistrationTime()
                ));
    }

    public CompletableFuture<ParticipantEntity> findById(String participantId) {
        return queryGateway.query(
                new FindParticipantQuery(participantId),
                ResponseTypes.instanceOf(ParticipantEntity.class)
        );
    }

    public CompletableFuture<ParticipantDataEntity> findParticipantDataById(String participantDataId) {
        return queryGateway.query(
                new FindParticipantDataQuery(participantDataId),
                ResponseTypes.instanceOf(ParticipantDataEntity.class)
        );
    }

    public List<ParticipantEntity> findAllParticipants(Pageable pageable) {
        return queryGateway.query(
                new FindAllParticipantQuery(pageable),
                ResponseTypes.multipleInstancesOf(ParticipantEntity.class)).join();
    }
}
