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
public class NamespaceRequest {

    @NotNull(message = "namespace must not be null")
    private String namespace;
}