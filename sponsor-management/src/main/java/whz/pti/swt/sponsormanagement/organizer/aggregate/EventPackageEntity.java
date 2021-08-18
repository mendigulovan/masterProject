package whz.pti.swt.sponsormanagement.organizer.aggregate;

import lombok.*;
import org.hibernate.annotations.Proxy;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorStatus;

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
@EqualsAndHashCode
public class EventPackageEntity {
    @Id
    private String packageId;
    private String description;
    @Enumerated(EnumType.STRING)
    private SponsorStatus status;


}
