package macan.marko.featureflags.repository;

import macan.marko.featureflags.entity.Participant;
import macan.marko.featureflags.entity.ParticipantId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface ParticipantRepository extends JpaRepository<Participant, ParticipantId> {

    List<Participant> findAllByParticipantId_FeatureAvailabilityRuleId(String featureAvailabilityRuleId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    void deleteAllByParticipantId_FeatureAvailabilityRuleId(String featureAvailabilityRuleId);

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Participant p set p.participantId.userId = :newUserId " +
            "where p.participantId.userId = :oldUserId and p.participantId.featureAvailabilityRuleId = :featureAvailabilityRuleId")
    void updateParticipant(@Param("newUserId") String newUserId,
                           @Param("oldUserId") String oldUserId,
                           @Param("featureAvailabilityRuleId") String featureAvailabilityRuleId);
}
