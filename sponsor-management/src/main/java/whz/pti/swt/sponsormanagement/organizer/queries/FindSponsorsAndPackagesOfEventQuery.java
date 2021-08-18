package whz.pti.swt.sponsormanagement.organizer.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindSponsorsAndPackagesOfEventQuery {
    String id;
    Pageable pageable;

}
