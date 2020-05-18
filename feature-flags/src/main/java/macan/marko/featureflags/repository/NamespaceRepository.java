package macan.marko.featureflags.repository;

import macan.marko.featureflags.entity.Namespace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface NamespaceRepository extends JpaRepository<Namespace, String> {

    @Transactional
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update Namespace n set n.namespace = :newNamespaceName where n.namespace = :oldNamespaceName")
    void updateNamespace(@Param("newNamespaceName") String newNamespaceName, @Param("oldNamespaceName") String oldNamespaceName);
}
