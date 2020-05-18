package macan.marko.usermanagement.model;

import lombok.Value;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Value
public class Filter {

    @NotEmpty
    List<@NotEmpty List<@NotNull @Valid FilterClause>> filterMatrix;

    public List<List<FilterClause>> getFilterMatrix() {
        List<List<FilterClause>> temp = new ArrayList<>();
        for (List<FilterClause> clauses : filterMatrix) {
            temp.add(Collections.unmodifiableList(clauses));
        }
        return Collections.unmodifiableList(temp);
    }
}
