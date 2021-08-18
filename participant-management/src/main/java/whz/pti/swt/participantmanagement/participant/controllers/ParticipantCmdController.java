package whz.pti.swt.participantmanagement.participant.controllers;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataEntity;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantEntity;
import whz.pti.swt.participantmanagement.participant.services.ParticipantService;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = "/participants/participant")
@Api(value = "Participant Commands", description = "Participant Commands API")
@AllArgsConstructor
public class ParticipantCmdController {

    private final ParticipantService participantService;

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<ParticipantEntity> addParticipant(@RequestBody ParticipantEntity dto) {
        return this.participantService.addParticipant(dto);
    }

    @PostMapping(value = "/{participantId}/participantData")
    @ResponseStatus(value = HttpStatus.CREATED)
    public CompletableFuture<ParticipantDataEntity> fillDataParticipant(@RequestBody ParticipantDataEntity dto, @PathVariable(value = "participantId") String participantId, String eventId) {
        return participantService.fillDataParticipant(participantId, dto);
    }

    @PutMapping(value = "/{participantId}")
    @ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)
    public CompletableFuture<ParticipantEntity> updateParticipantStatus(@PathVariable(value = "participantId") String participantId, String eventId) {
        return this.participantService.updateEmailSatusParticipant(participantId, eventId);
    }
}
