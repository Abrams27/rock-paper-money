package pl.uw.mim.jnp.rock.paper.money.app.usecases.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameInfoResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class GameInfo {

  public Mono<GameInfoResponse> execute(Long gameId) {
    return Mono.empty();
  }
}
