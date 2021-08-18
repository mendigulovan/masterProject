package whz.pti.swt.sponsormanagement.organizer.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class EventEntity {
    @Id
    private String eventId;
    private String description;
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<EventPackageEntity> packageList;
    @OneToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<SponsorChosenPackageEntity> packageChosenBySponsorList;

}

