package whz.pti.swt.sponsormanagement.organizer.aggregate;

import lombok.*;
import org.hibernate.annotations.Proxy;

import javax.persistence.Entity;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Proxy(lazy = false)
@Builder
@Entity
@EqualsAndHashCode
public class SponsorChosenPackageEntity {
    @Id
    private String sponsorId;
    private String eventPackageId;
    private String firmName;
    private String packageDescription;

}
