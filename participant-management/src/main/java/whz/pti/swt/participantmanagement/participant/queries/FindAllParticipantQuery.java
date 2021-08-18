package whz.pti.swt.participantmanagement.participant.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAllParticipantQuery {
    Pageable page;
}
