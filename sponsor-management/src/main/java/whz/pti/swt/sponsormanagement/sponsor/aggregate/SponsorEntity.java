package whz.pti.swt.sponsormanagement.sponsor.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
@Builder
@Entity
public class SponsorEntity {
    @Id
    private String SponsorId;
    private String emailStatus;
    private String eventId;
    private String responseStatus;
    private String firmName;
    @Enumerated(EnumType.STRING)
    private SponsorStatus status;
}
