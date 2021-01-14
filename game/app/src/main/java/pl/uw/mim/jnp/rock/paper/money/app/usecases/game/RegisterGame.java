package pl.uw.mim.jnp.rock.paper.money.app.usecases.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameRegistrationDto;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RegisterGame {

  public Mono<Void> execute(GameRegistrationDto gameRegistration) {
    // TODO
    return Mono.empty();
  }
}
