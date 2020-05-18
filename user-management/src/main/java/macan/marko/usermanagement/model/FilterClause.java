package macan.marko.usermanagement.model;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class FilterClause {

    @NotBlank
    String column;

    @NotBlank
    String condition;

    @NotBlank
    String criteria;
}
