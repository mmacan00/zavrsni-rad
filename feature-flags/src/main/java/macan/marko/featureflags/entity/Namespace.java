package macan.marko.featureflags.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
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
    @OneToMany(mappedBy = "namespace", cascade = CascadeType.ALL)
    List<FeatureAvailabilityRule> featureAvailabilityRules;
}
