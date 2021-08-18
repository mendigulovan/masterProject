package whz.pti.swt.participantmanagement.participant.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataEntity;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantEntity;
import whz.pti.swt.participantmanagement.participant.services.ParticipantService;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/participants/participant ")
@Api(value = "Participant Queries")
@AllArgsConstructor
public class ParticipantQueryController {
    private final ParticipantService participantQueryService;
    @Autowired
    private final QueryGateway queryGateway;

    @GetMapping("/{participantId}")
    public CompletableFuture<ParticipantEntity> findById(@PathVariable("participantId") String participantId) {
        return participantQueryService.findById(participantId);
    }

    @GetMapping("/{participantId}/participantData/{participantDataId}")
    public CompletableFuture<ParticipantDataEntity> findParticipantDatatById(@PathVariable("participantDataId") String participantDataId) {
        return participantQueryService.findParticipantDataById(participantDataId);
    }

    @GetMapping("/participant")
    public List<ParticipantEntity> findAllParticipants(Pageable pageable) {

        return participantQueryService.findAllParticipants(pageable);
    }
}