package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.GameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api.PostgresGameHistoryRepository;
import reactor.core.publisher.Flux;

@Service
@AllArgsConstructor
public class GameHistoryService {

  private final PostgresGameHistoryRepository postgresGameHistoryRepository;

  public Flux<GameHistoryDto> getHistoryForUser(Long userId) {

  }
}
