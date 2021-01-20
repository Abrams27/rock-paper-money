package pl.uw.mim.jnp.rock.paper.money.app.usecases.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.GameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api.PostgresGameHistoryRepository;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class GetGamesHistory {

  private final PostgresGameHistoryRepository postgresGameHistoryRepository;

  public Flux<GameHistoryDto> execute(Long userId) {
    return Flux.empty();
  }

}
