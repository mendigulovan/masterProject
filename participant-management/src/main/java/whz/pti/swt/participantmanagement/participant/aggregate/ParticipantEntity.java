package whz.pti.swt.participantmanagement.participant.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
@Builder
@Entity
public class ParticipantEntity {
    @Id
    private String id;
    private String emailStatus;
    private String name;


}
