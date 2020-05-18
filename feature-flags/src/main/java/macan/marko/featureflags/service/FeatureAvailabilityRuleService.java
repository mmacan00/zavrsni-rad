package macan.marko.featureflags.service;

import lombok.AllArgsConstructor;
import macan.marko.featureflags.client.UserClient;
import macan.marko.featureflags.entity.AndOperation;
import macan.marko.featureflags.entity.FeatureAvailabilityRule;
import macan.marko.featureflags.entity.Namespace;
import macan.marko.featureflags.entity.OrOperation;
import macan.marko.featureflags.mapper.FeatureAvailabilityRuleMapper;
import macan.marko.featureflags.model.AndOperationModel;
import macan.marko.featureflags.model.FeatureAvailabilityRuleModel;
import macan.marko.featureflags.model.Filter;
import macan.marko.featureflags.model.OrOperationModel;
import macan.marko.featureflags.repository.FeatureAvailabilityRuleRepository;
import macan.marko.featureflags.repository.NamespaceRepository;
import macan.marko.featureflags.repository.OrOperationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static macan.marko.featureflags.client.UserClient.getUsersMatchingFilter;
import static macan.marko.featureflags.client.UserClient.mapToFilter;

@Service
@Validated
@AllArgsConstructor
@Transactional(readOnly = true)
public class FeatureAvailabilityRuleService {

    private final FeatureAvailabilityRuleRepository repository;
    private final OrOperationRepository orOperationRepository;
    private final NamespaceRepository namespaceRepository;
    private final ParticipantService participantService;

    public FeatureAvailabilityRuleModel get(String id) {
        return FeatureAvailabilityRuleMapper.toModel(repository.findById(id).orElseThrow(NoSuchElementException::new));
    }

    public List<FeatureAvailabilityRuleModel> getAll() {
        return repository.findAll().stream().map(FeatureAvailabilityRuleMapper::toModel).collect(Collectors.toList());
    }

    public List<String> getAttributes() throws IOException {
        return UserClient.getAttributes();
    }

    @Transactional
    public void save(FeatureAvailabilityRuleModel model) throws IOException {
        FeatureAvailabilityRule rule = new FeatureAvailabilityRule();

        Namespace namespace = namespaceRepository.findById(model.getNamespace()).orElseThrow(NoSuchElementException::new);
        rule.setNamespace(namespace);
        rule.setFeature(model.getFeature());
        rule.setDescription(model.getDescription());
        rule.setActive(model.getActive());

        setOperations(model, rule);

        repository.save(rule);

        Filter filter = mapToFilter(rule.getOrOperations());

        List<String> userIds = getUsersMatchingFilter(filter);

        participantService.saveAllForFeatureAvailabilityRule(rule.getId(), userIds);
    }

    @Transactional
    public void update(FeatureAvailabilityRuleModel model) throws IOException {
        FeatureAvailabilityRule rule = repository.findById(model.getId()).orElseThrow(NoSuchElementException::new);

        checkAndSetNamespaceAndFeature(model, rule);

        if (nonNull(model.getDescription()) && !Objects.equals(rule.getDescription(), model.getDescription())) {
            rule.setDescription(model.getDescription());
        }
        if (nonNull(model.getActive()) && !Objects.equals(rule.getActive(), model.getActive())) {
            rule.setActive(model.getActive());
        }

        if (nonNull(model.getOrOperations())) {
            orOperationRepository.deleteAllByAvailabilityRule_Id(model.getId());
            setOperations(model, rule);
        }
        repository.save(rule);

        Filter filter = mapToFilter(rule.getOrOperations());

        List<String> userIds = getUsersMatchingFilter(filter);

        participantService.updateAllForFeatureAvailabilityRule(rule.getId(), userIds);
    }

    @Transactional
    public void delete(String id) {
        repository.deleteById(id);
    }

    private void checkAndSetNamespaceAndFeature(FeatureAvailabilityRuleModel model, FeatureAvailabilityRule rule) {
        if (model.getNamespace().equals(rule.getNamespace().getNamespace()) && model.getFeature().equals(rule.getFeature())) {
            return;
        }
        if (nonNull(model.getFeature()) && nonNull(model.getNamespace())) {
            validateNamespaceFeatureConstraint(model.getNamespace(), model.getFeature());
            if (!Objects.equals(rule.getFeature(), model.getFeature())) {
                rule.setFeature(model.getFeature());
            }
            if (!Objects.equals(rule.getNamespace().getNamespace(), model.getNamespace())) {
                rule.getNamespace().setNamespace(model.getNamespace());
            }
        } else if (nonNull(model.getFeature()) && isNull(model.getNamespace())) {
            validateNamespaceFeatureConstraint(rule.getNamespace().getNamespace(), model.getFeature());
            if (!Objects.equals(rule.getFeature(), model.getFeature())) {
                rule.setFeature(model.getFeature());
            }
        } else if (isNull(model.getFeature()) && nonNull(model.getNamespace())) {
            validateNamespaceFeatureConstraint(model.getNamespace(), rule.getFeature());
            if (!Objects.equals(rule.getNamespace().getNamespace(), model.getNamespace())) {
                rule.getNamespace().setNamespace(model.getNamespace());
            }
        }
    }

    private void validateNamespaceFeatureConstraint(String namespace, String feature) {
        if (repository.existsByNamespace_NamespaceAndFeature(namespace, feature)) {
            throw new IllegalArgumentException("That combination of namespace + feature exists!");
        }
    }

    private void setOperations(FeatureAvailabilityRuleModel model, FeatureAvailabilityRule rule) {
        List<OrOperation> orOperations = new ArrayList<>();
        for (OrOperationModel orOperationModel : model.getOrOperations()) {
            OrOperation orOperation = new OrOperation();
            orOperation.setAvailabilityRule(rule);

            List<AndOperation> andOperations = new ArrayList<>();
            for (AndOperationModel andOperationModel : orOperationModel.getAndOperations()) {
                AndOperation andOperation = new AndOperation();
                andOperation.setOrOperation(orOperation);
                andOperation.setGroupStrategy(andOperationModel.getGroupStrategy());
                andOperation.setCondition(andOperationModel.getCondition());
                andOperation.setCriteria(andOperationModel.getCriteria());
                andOperations.add(andOperation);
            }
            orOperation.setAndOperations(andOperations);
            orOperations.add(orOperation);
        }
        rule.setOrOperations(orOperations);
    }
}
