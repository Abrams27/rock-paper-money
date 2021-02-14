package pl.uw.mim.jnp.rock.paper.money.app.usecases.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.UserGameResponseDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.GameInfoService;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class NewGame {

  private final GameInfoService gameInfoService;

  public Mono<UserGameResponseDto> execute(String username) {
    return gameInfoService.getRecentGameForUsername(username);
  }
}
