package macan.marko.featureflags.api;

import lombok.AllArgsConstructor;
import macan.marko.featureflags.service.FeatureFlagService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/feature-flag")
@AllArgsConstructor
public class FeatureFlagController {

    private final FeatureFlagService service;

    @GetMapping(params = {"namespace", "feature", "userId"})
    public boolean isUserAllowed(
            @RequestParam @NotBlank String namespace,
            @RequestParam @NotBlank String feature,
            @RequestParam @NotBlank String userId) {
        return service.isUserAllowed(namespace, feature, userId);
    }

    @GetMapping(params = {"namespace", "feature"})
    public List<String> getAllForFeatureAvailabilityRule(
            @RequestParam @NotBlank String namespace,
            @RequestParam @NotBlank String feature) {
        return service.getAllForFeatureAvailabilityRule(namespace, feature);
    }
}
