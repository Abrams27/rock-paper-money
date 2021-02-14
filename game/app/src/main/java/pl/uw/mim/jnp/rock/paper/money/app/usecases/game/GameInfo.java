package pl.uw.mim.jnp.rock.paper.money.app.usecases.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameProgress;
import pl.uw.mim.jnp.rock.paper.money.app.services.GameInfoService;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class GameInfo {

  private final GameInfoService gameInfoService;

  public Mono<GameProgress> execute(Long gameId) {
    return gameInfoService.getGameProgress(gameId);
  }
}
