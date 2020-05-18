package macan.marko.featureflags.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "participant", indexes = {@Index(columnList = "feature_availability_rule_id")})
public class Participant {

    @EmbeddedId
    ParticipantId participantId;

    @MapsId("feature_availability_rule_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private FeatureAvailabilityRule featureAvailabilityRule;
}
