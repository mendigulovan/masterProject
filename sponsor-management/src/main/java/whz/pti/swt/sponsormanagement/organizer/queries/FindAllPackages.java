package whz.pti.swt.sponsormanagement.organizer.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventPackageEntity;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FindAllPackages {
    String id;
    List<EventPackageEntity> list;
}
