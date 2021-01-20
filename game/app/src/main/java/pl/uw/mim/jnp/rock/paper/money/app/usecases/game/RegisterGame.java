package pl.uw.mim.jnp.rock.paper.money.app.usecases.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameRegistrationDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.GameService;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RegisterGame {

  private final GameService gameService;

  public Mono<Void> execute(GameRegistrationDto gameRegistration) {
    gameService.registerGame(
        gameRegistration.getGameId(),
        gameRegistration.getPlayer1Id(),
        gameRegistration.getPlayer2Id(),
        gameRegistration.getStake());

    return Mono.empty();
  }
}
