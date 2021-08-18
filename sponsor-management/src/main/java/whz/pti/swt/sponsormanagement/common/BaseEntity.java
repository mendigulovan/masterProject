package whz.pti.swt.sponsormanagement.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@EqualsAndHashCode
@MappedSuperclass
@Getter
@Setter
public abstract class BaseEntity<PK extends Serializable> {

    @Id
    @GeneratedValue
    private PK id;

    }
