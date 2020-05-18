package macan.marko.featureflags.service;

import lombok.AllArgsConstructor;
import macan.marko.featureflags.entity.FeatureAvailabilityRule;
import macan.marko.featureflags.entity.Participant;
import macan.marko.featureflags.entity.ParticipantId;
import macan.marko.featureflags.model.Filter;
import macan.marko.featureflags.repository.FeatureAvailabilityRuleRepository;
import macan.marko.featureflags.repository.ParticipantRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static macan.marko.featureflags.client.UserClient.getUsersMatchingFilter;
import static macan.marko.featureflags.client.UserClient.mapToFilter;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class ParticipantService {

    private final ParticipantRepository repository;
    private final FeatureAvailabilityRuleRepository ruleRepository;

    @Transactional
    public void saveAllForFeatureAvailabilityRule(String featureAvailabilityRuleId, List<String> userIds) {
        saveParticipants(featureAvailabilityRuleId, userIds);
    }

    @Transactional
    public void updateAllForFeatureAvailabilityRule(String featureAvailabilityRuleId, List<String> userIds) {
        deleteAllForFeatureAvailabilityRule(featureAvailabilityRuleId);
        saveParticipants(featureAvailabilityRuleId, userIds);
    }

    @Transactional
    public void deleteAllForFeatureAvailabilityRule(String featureAvailabilityRuleId) {
        repository.deleteAllByParticipantId_FeatureAvailabilityRuleId(featureAvailabilityRuleId);
    }

    @Transactional
    @RabbitListener(queues = "#{autoDeleteQueue.name}")
    public void updateParticipants() throws InterruptedException, IOException {
        TimeUnit.SECONDS.sleep(3);

        for (FeatureAvailabilityRule rule : ruleRepository.findAll()) {
            Filter filter = mapToFilter(rule.getOrOperations());

            List<String> userIds = getUsersMatchingFilter(filter);

            updateAllForFeatureAvailabilityRule(rule.getId(), userIds);
        }
    }

    private void saveParticipants(String featureAvailabilityRuleId, List<String> userIds) {
        List<Participant> participants = new ArrayList<>();
        for (String userId : userIds) {
            ParticipantId id = new ParticipantId(featureAvailabilityRuleId, userId);
            Participant participant = new Participant();
            participant.setParticipantId(id);
            participants.add(participant);
        }
        repository.saveAll(participants);
    }
}
