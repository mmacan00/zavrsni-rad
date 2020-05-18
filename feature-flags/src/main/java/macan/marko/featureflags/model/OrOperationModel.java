package macan.marko.featureflags.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrOperationModel {

    @NotEmpty(message = "A list andOperations must not be null")
    @Valid
    private List<AndOperationModel> andOperations = new ArrayList<>();
}
