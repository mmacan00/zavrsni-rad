package macan.marko.featureflags.service;

import lombok.AllArgsConstructor;
import macan.marko.featureflags.entity.FeatureAvailabilityRule;
import macan.marko.featureflags.repository.FeatureAvailabilityRuleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
@Transactional(readOnly = true)
public class FeatureFlagService {

    private final FeatureAvailabilityRuleRepository ruleRepository;

    public List<String> getAllForFeatureAvailabilityRule(@NotBlank String namespace, @NotBlank String feature) {
        FeatureAvailabilityRule rule = ruleRepository.findByNamespace_NamespaceAndFeatureAndActiveIsTrue(namespace, feature)
                .orElseThrow(EntityNotFoundException::new);

        return rule.getParticipants()
                .stream()
                .map(participant -> participant.getParticipantId().getUserId())
                .collect(Collectors.toList());
    }

    public boolean isUserAllowed(@NotBlank String namespace, @NotBlank String feature, @NotBlank String userId) {
        Optional<FeatureAvailabilityRule> rule = ruleRepository.findByNamespace_NamespaceAndFeatureAndActiveIsTrue(namespace, feature);

        return rule.map(featureAvailabilityRule -> featureAvailabilityRule.getParticipants()
                .stream()
                .map(participant -> participant.getParticipantId().getUserId())
                .anyMatch(userId::equals)).orElse(false);
    }
}
