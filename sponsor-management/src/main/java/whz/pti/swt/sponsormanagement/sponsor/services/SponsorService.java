package whz.pti.swt.sponsormanagement.sponsor.services;

import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;
import whz.pti.swt.sponsormanagement.coreapi.commands.*;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorEntity;
import whz.pti.swt.sponsormanagement.sponsor.queries.FindAllSponsorsQuery;
import whz.pti.swt.sponsormanagement.sponsor.queries.FindSponsorQuery;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class SponsorService {

    private final CommandGateway gateway;
    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public CompletableFuture<SponsorEntity> addSponsorToDb(SponsorEntity dto) throws InterruptedException {
        return gateway.send(new AddSponsorCmd(UUID.randomUUID().toString(), dto.getFirmName(), dto.getStatus()));
    }

    public CompletableFuture<SponsorEntity> updateEmailStatusSponsor(String sponsorId, String eventId) {
        return gateway.send(new SponsorReceiveEmailCmd(
                sponsorId, eventId
        ));
    }

    public CompletableFuture<SponsorEntity> applySponsorForEvent(String sponsorId, SponsorEntity dto) {
        return gateway.send(new ApplySponsorForEventCmd(
                sponsorId, dto.getEventId(), dto.getFirmName()
        ));
    }

    public CompletableFuture<SponsorEntity> getConfirmation(String sponsorId, String packageId) {
        return gateway.send(new GetSponsorConfirmationCmd(
                sponsorId, packageId
        ));
    }

    public CompletableFuture<SponsorEntity> getRefusal(String sponsorId) {
        return gateway.send(new GetSponsorRefusalCmd(
                sponsorId
        ));
    }


    public CompletableFuture<SponsorEntity> findById(String sponsorId) {
        return this.queryGateway.query(
                new FindSponsorQuery(sponsorId),
                ResponseTypes.instanceOf(SponsorEntity.class)
        );
    }

    public List<SponsorEntity> findAllSponsors() {
        return this.queryGateway.query(
                new FindAllSponsorsQuery(),
                ResponseTypes.multipleInstancesOf(SponsorEntity.class)
        ).join();
    }

}
