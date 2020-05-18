package macan.marko.featureflags.api;

import lombok.AllArgsConstructor;
import macan.marko.featureflags.service.NamespaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/namespace")
@AllArgsConstructor
public class NamespaceController {

    private final NamespaceService service;

    @GetMapping
    public List<String> getAll() {
        return service.getAll();
    }

    @PostMapping
    public void save(@RequestBody String namespace) {
        service.save(namespace);
    }

    @PatchMapping("/{oldNamespaceName}")
    public void update(@PathVariable String oldNamespaceName, @RequestBody String newNamespaceName) {
        service.update(oldNamespaceName, newNamespaceName);
    }

    @DeleteMapping("/{namespace}")
    public void delete(@PathVariable String namespace) {
        service.delete(namespace);
    }
}
