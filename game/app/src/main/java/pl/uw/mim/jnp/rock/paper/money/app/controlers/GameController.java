package pl.uw.mim.jnp.rock.paper.money.app.controlers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.uw.mim.jnp.rock.paper.money.api.endpoints.GameApi;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameEntranceDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameRegistrationDto;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.game.EnterGame;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.game.RegisterGame;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class GameController implements GameApi {

  private final RegisterGame registerGame;
  private final EnterGame enterGame;

  @Override
  public Mono<Void> registerGame(GameRegistrationDto gameRegistration) {
    return registerGame.execute(gameRegistration);
  }

  @Override
  public Mono<Void> enterGame(GameEntranceDto gameEntrance) {
    return enterGame.execute(gameEntrance);
  }
}
