package macan.marko.featureflags.api;

import lombok.AllArgsConstructor;
import macan.marko.featureflags.model.FeatureAvailabilityRuleModel;
import macan.marko.featureflags.service.FeatureAvailabilityRuleService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/feature-availability-rule")
@AllArgsConstructor
public class FeatureAvailabilityRuleController {

    private final FeatureAvailabilityRuleService service;

    @GetMapping("/{id}")
    public FeatureAvailabilityRuleModel get(@PathVariable String id) {
        return service.get(id);
    }

    @GetMapping
    public List<FeatureAvailabilityRuleModel> getAll() {
        return service.getAll();
    }

    @GetMapping("/attributes")
    public List<String> getAttributes() throws IOException {
        return service.getAttributes();
    }

    @PostMapping
    public void save(@RequestBody FeatureAvailabilityRuleModel model) throws IOException {
        service.save(model);
    }

    @PatchMapping
    public void update(@RequestBody FeatureAvailabilityRuleModel model) throws IOException {
        service.update(model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable String id) {
        service.delete(id);
    }
}
