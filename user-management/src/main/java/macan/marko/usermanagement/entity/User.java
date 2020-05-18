package macan.marko.usermanagement.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "userName")
})
public class User {

    @Id
    @GeneratedValue(generator = "uuid-v4")
    @GenericGenerator(name = "uuid-v4", strategy = "uuid2")
    @Column(columnDefinition = "char(36)", updatable = false, nullable = false)
    @Size(min = 36, max = 36)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    @Column(unique = true)
    private String userName;

    @Email
    @NotNull
    private String email;

    @Past
    @NotNull
    @Column(columnDefinition = "date")
    private Date dateOfBirth;

    @NotNull
    private String city;

    @NotNull
    private String country;

    @NotNull
    private Gender gender;

    @Min(1)
    @Max(300)
    @NotNull
    private Short heightCm;

    @Min(1)
    @Max(500)
    @NotNull
    private Short weightKg;
}
