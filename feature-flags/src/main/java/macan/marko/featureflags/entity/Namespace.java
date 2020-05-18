package macan.marko.featureflags.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "namespace")
public class Namespace {

    @Id
    private String namespace;

    @ToString.Exclude
    @OneToMany(mappedBy = "namespace")
    List<FeatureAvailabilityRule> featureAvailabilityRules;
}
