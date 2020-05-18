package macan.marko.featureflags.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "and_operation", uniqueConstraints = {@UniqueConstraint(columnNames = {"or_operation_id", "group_strategy"})})
public class AndOperation extends AbstractEntity {

    @Column(name = "group_strategy", nullable = false)
    private String groupStrategy;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GroupCondition condition;

    @Column(columnDefinition = "text", nullable = false)
    private String criteria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "or_operation_id", nullable = false)
    private OrOperation orOperation;
}
