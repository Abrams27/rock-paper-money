package pl.uw.mim.jnp.rock.paper.money.app.usecases.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameEntranceDto;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class EnterGame {

  public Mono<Void> execute(GameEntranceDto gameEntrance) {
    // TODO

    return Mono.empty();
  }
}
