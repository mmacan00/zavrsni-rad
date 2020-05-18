package macan.marko.featureflags.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
abstract class AbstractEntity {

    @Id
    @GeneratedValue(generator = "uuid-v4")
    @GenericGenerator(name = "uuid-v4", strategy = "uuid2")
    @Column(length = 36)
    private String id;
}
