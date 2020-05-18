package macan.marko.featureflags.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EligibleUser {

    private String id;

    private String userName;
}
