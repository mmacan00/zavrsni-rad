package macan.marko.featureflags.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NamespaceUpdateRequest {

    @NotNull(message = "oldNamespaceName must not be null")
    private String oldNamespaceName;

    @NotNull(message = "newNamespaceName must not be null")
    private String newNamespaceName;
}
