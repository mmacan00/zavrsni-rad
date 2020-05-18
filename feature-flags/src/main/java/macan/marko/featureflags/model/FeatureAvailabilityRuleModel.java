package macan.marko.featureflags.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeatureAvailabilityRuleModel {

    private String id;

    @NotNull(message = "feature must not be null")
    private String feature;

    @NotNull(message = "namespace must not be null")
    private String namespace;

    private String description;

    @NotNull(message = "active must not be null")
    private Boolean active;

    @NotEmpty(message = "A list orOperations must not be null or empty")
    @Valid
    private List<OrOperationModel> orOperations = new ArrayList<>();
}
