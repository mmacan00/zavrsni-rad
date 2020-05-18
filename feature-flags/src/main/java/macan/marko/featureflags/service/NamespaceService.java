package macan.marko.featureflags.service;

import lombok.AllArgsConstructor;
import macan.marko.featureflags.entity.Namespace;
import macan.marko.featureflags.repository.NamespaceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class NamespaceService {

    private final NamespaceRepository repository;

    public List<String> getAll() {
        return repository.findAll().stream().map(Namespace::getNamespace).collect(Collectors.toList());
    }

    @Transactional
    public void save(String namespaceName) {
        Namespace namespace = new Namespace();
        namespace.setNamespace(namespaceName);
        repository.save(namespace);
    }

    @Transactional
    public void update(String oldNamespaceName, String newNamespaceName) {
        repository.updateNamespace(newNamespaceName, oldNamespaceName);
    }

    @Transactional
    public void delete(String namespaceName) {
        repository.deleteById(namespaceName);
    }
}
