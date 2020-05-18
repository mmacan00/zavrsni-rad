package macan.marko.featureflags.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupCondition {

    IS("IS", "="),
    IS_NOT("IS_NOT", "<>");

    private final String value;
    private final String sqlOperator;
}
