package macan.marko.featureflags.repository;

import macan.marko.featureflags.entity.OrOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface OrOperationRepository extends JpaRepository<OrOperation, String> {

    @Transactional
    @Modifying(flushAutomatically = true, clearAutomatically = true)
    void deleteAllByAvailabilityRule_Id(String id);
}
