package pl.uw.mim.jnp.rock.paper.money.app.usecases.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameResultResponseDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.GameInfoService;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class GameResult {

  private final GameInfoService gameInfoService;

  public Mono<GameResultResponseDto> execute(Long gameId, String username) {
    return gameInfoService.getGame(gameId, username);
  }
}
