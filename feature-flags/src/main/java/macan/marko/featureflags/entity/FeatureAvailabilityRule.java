package macan.marko.featureflags.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "feature_availability_rule",
        indexes = {@Index(columnList = "namespace")},
        uniqueConstraints = {@UniqueConstraint(columnNames = {"namespace", "feature"})})
public class FeatureAvailabilityRule extends AbstractEntity {

    @JoinColumn(name = "namespace", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Namespace namespace;

    @Column(nullable = false)
    private String feature;

    private String description;

    @Column(nullable = false)
    private Boolean active;

    @ToString.Exclude
    @OneToMany(mappedBy = "availabilityRule", cascade = CascadeType.ALL)
    private List<OrOperation> orOperations;

    @ToString.Exclude
    @OneToMany(mappedBy = "featureAvailabilityRule", cascade = CascadeType.ALL)
    private List<Participant> participants;
}
