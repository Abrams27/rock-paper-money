package pl.uw.mim.jnp.rock.paper.money.app.usecases.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.exceptions.InvalidRegistrationException;
import pl.uw.mim.jnp.rock.paper.money.models.GameRegistrationDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.GameService;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RegisterGame {

  private final GameService gameService;

  public Mono<Void> execute(GameRegistrationDto gameRegistration) {
    return Mono.just(gameRegistration)
        .map(
            registration ->
                gameService.registerGame(
                    registration.getGameId(),
                    registration.getPlayer1Username(),
                    registration.getPlayer2Username(),
                    registration.getStake()))
        .filter(o -> o)
        .switchIfEmpty(Mono.error(new InvalidRegistrationException()))
        .then();
  }
}
