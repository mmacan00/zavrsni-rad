package macan.marko.usermanagement.api;

import macan.marko.usermanagement.model.Filter;
import macan.marko.usermanagement.service.UserService;
import macan.marko.usermanagement.entity.User;
import macan.marko.usermanagement.model.EligibleUser;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Validated
@AllArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    @GetMapping("/{id}")
    public User get(@NotBlank @PathVariable String id) {
        return service.get(id);
    }

    @GetMapping
    public List<User> getAll() {
        return service.getAll();
    }

    @GetMapping("/attributes")
    public List<String> getAllAttributes() {
        return Arrays.stream(User.class.getDeclaredFields()).map(Field::getName).collect(Collectors.toList());
    }

    @PostMapping("/filter")
    public List<EligibleUser> getByFilter(@NotNull @Valid @RequestBody Filter filter) {
        return service.getFilteredUsers(filter);
    }

    @PostMapping
    public User save(@NotNull @Valid @RequestBody User user) {
        return service.save(user);
    }

    @PutMapping("/{id}")
    public User update(@NotBlank @PathVariable String id, @NotNull @Valid @RequestBody User user) {
        user.setId(id);
        return service.update(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@NotBlank @PathVariable String id) {
        service.delete(id);
    }
}
