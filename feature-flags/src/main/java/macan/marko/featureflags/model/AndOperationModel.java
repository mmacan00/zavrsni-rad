package macan.marko.featureflags.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import macan.marko.featureflags.entity.GroupCondition;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AndOperationModel {

    @NotNull(message = "groupStrategy must not be null")
    private String groupStrategy;

    @NotNull(message = "condition must not be null")
    private GroupCondition condition;

    @NotNull(message = "criteria must not be null")
    private String criteria;
}
