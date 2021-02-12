package pl.uw.mim.jnp.rock.paper.money.persistence.redis.api;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.LoggedUserEntry;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo.LoggedUserRepository;

@Component
@AllArgsConstructor
public class RedisLoggedUserRepository {

  private final LoggedUserRepository loggedUserRepository;

  public void saveUsername(String username) {
    loggedUserRepository.save(getLoggedUserEntryForUsername(username));
  }

  private LoggedUserEntry getLoggedUserEntryForUsername(String username) {
    return LoggedUserEntry.builder()
        .id(username)
        .build();
  }

  public Optional<String> findUsername(String username) {
    return loggedUserRepository.findById(username)
        .map(LoggedUserEntry::getId);
  }

  public void removeUsername(String username) {
    loggedUserRepository.deleteById(username);
  }
}
