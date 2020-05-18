package macan.marko.featureflags.repository;

import macan.marko.featureflags.entity.FeatureAvailabilityRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface FeatureAvailabilityRuleRepository extends JpaRepository<FeatureAvailabilityRule, String> {

    boolean existsByNamespace_NamespaceAndFeature(String namespace, String feature);

    Optional<FeatureAvailabilityRule> findByNamespace_NamespaceAndFeatureAndActiveIsTrue(String namespace, String feature);

}
