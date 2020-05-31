package macan.marko.usermanagement.service;

import macan.marko.usermanagement.entity.User;
import macan.marko.usermanagement.model.Filter;
import macan.marko.usermanagement.model.FilterClause;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserMatcher {

    private UserMatcher() {
    }

    public static Specification<User> matchesFilter(Filter filter) {
        return (user, cq, cb) -> {
            List<Predicate> orPredicates = new ArrayList<>();

            for (List<FilterClause> clauses : filter.getFilterMatrix()) {
                List<Predicate> andPredicates = getAndPredicates(user, cb, clauses);
                orPredicates.add(cb.and(andPredicates.toArray(new Predicate[0])));
            }
            return cb.or(orPredicates.toArray(new Predicate[0]));
        };
    }

    private static List<Predicate> getAndPredicates(Root<User> user, CriteriaBuilder cb, List<FilterClause> clauses) {
        List<Predicate> andPredicates = new ArrayList<>();
        for (FilterClause clause : clauses) {
            if (clause.getCondition().equals("=")) {
                andPredicates.add(cb.equal(user.get(clause.getColumn()), clause.getCriteria()));
            } else if (clause.getCondition().equals("<>")) {
                andPredicates.add(cb.notEqual(user.get(clause.getColumn()), clause.getCriteria()));
            }
        }
        return andPredicates;
    }
}
