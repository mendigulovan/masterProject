package whz.pti.swt.sponsormanagement.organizer.service;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import whz.pti.swt.sponsormanagement.coreapi.commands.CreateEventCmd;
import whz.pti.swt.sponsormanagement.coreapi.commands.DeclineSponsorRequestCmd;
import whz.pti.swt.sponsormanagement.coreapi.commands.ResponseSponsorRequestCmd;
import whz.pti.swt.sponsormanagement.coreapi.commands.SponsorRequestCmd;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventEntity;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventPackageEntity;
import whz.pti.swt.sponsormanagement.organizer.aggregate.SponsorChosenPackageEntity;
import whz.pti.swt.sponsormanagement.organizer.queries.*;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorEntity;


import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class EventCommandService {

    private final CommandGateway gateway;
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public CompletableFuture<EventEntity> createHackathonEvent(EventEntity dto) {
        return this.gateway.send(new CreateEventCmd(UUID.randomUUID().toString(),dto.getDescription()));
    }


    public CompletableFuture<SponsorChosenPackageEntity> sponsorRequest(String eventId, SponsorEntity dto) {
        return this.gateway.send(new SponsorRequestCmd(eventId, dto.getSponsorId(),dto.getStatus()));
    }


    public CompletableFuture<SponsorChosenPackageEntity> sponsorRequestApprove(String eventId, String sponsorId, String packageId) {
        return this.gateway.send(new ResponseSponsorRequestCmd(eventId, sponsorId, packageId));
    }


    public CompletableFuture<SponsorChosenPackageEntity> sponsorRequestDecline(String eventId, String sponsorId, String packageId) {
        return this.gateway.send(new DeclineSponsorRequestCmd(eventId, sponsorId, packageId));
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

    public CompletableFuture<EventPackageEntity> findPackageById(String eventId) {
        return this.queryGateway.query(
                new FindPackageQuery(eventId),
                ResponseTypes.instanceOf(EventPackageEntity.class)
        );
    }


    public List<EventPackageEntity> findAllPackagesOfEvent(String eventId, List<EventPackageEntity> list) {
        return this.queryGateway.query(
                new FindAllPackages(eventId, list),
                ResponseTypes.multipleInstancesOf(EventPackageEntity.class)
        ).join();

    }
    public List<EventEntity> findAllEvents() {
        return queryGateway.query(
                new FindAllEventsQuery(),
                ResponseTypes.multipleInstancesOf(EventEntity.class)).join();
    }


}
