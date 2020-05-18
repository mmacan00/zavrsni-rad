package macan.marko.featureflags.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "or_operation", uniqueConstraints = {@UniqueConstraint(columnNames = {"id", "feature_availability_rule_id"})})
public class OrOperation extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feature_availability_rule_id", nullable = false)
    private FeatureAvailabilityRule availabilityRule;

    @ToString.Exclude
    @OneToMany(mappedBy = "orOperation", cascade = CascadeType.ALL)
    private List<AndOperation> andOperations;
}
