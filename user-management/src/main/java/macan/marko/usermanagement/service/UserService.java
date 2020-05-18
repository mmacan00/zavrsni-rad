package macan.marko.usermanagement.service;

import macan.marko.usermanagement.entity.User;
import macan.marko.usermanagement.model.EligibleUser;
import macan.marko.usermanagement.model.Filter;
import macan.marko.usermanagement.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Validated
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository repository;
    private final RabbitTemplate template;
    private final FanoutExchange fanout;

    public User get(@NotBlank String id) {
        return repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public List<EligibleUser> getFilteredUsers(@NotNull @Valid Filter filter) {
        return repository.findAll(where(UserMatcher.matchesFilter(filter)))
                .stream()
                .map(user -> new EligibleUser(user.getId(), user.getUserName()))
                .collect(Collectors.toList());
    }

    @Transactional
    public User save(@NotNull @Valid User user) {
        template.convertAndSend(fanout.getName(), "", "saved");
        return repository.save(user);
    }

    @Transactional
    public User update(@NotNull @Valid User user) {
        repository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);
        template.convertAndSend(fanout.getName(), "", "updated");
        return repository.save(user);
    }

    @Transactional
    public void delete(@NotBlank String id) {
        template.convertAndSend(fanout.getName(), "", "deleted");
        repository.deleteById(id);
    }
}
