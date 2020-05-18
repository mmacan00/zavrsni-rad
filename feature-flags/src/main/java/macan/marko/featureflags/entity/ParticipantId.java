package macan.marko.featureflags.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class ParticipantId implements Serializable {

    private static final long serialVersionUID = 8863837834278116431L;

    @Column(name = "feature_availability_rule_id", length = 36)
    private String featureAvailabilityRuleId;

    @Column(name = "user_id")
    private String userId;
}
