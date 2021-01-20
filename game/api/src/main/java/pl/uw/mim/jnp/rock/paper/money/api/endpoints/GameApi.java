package pl.uw.mim.jnp.rock.paper.money.api.endpoints;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameEntranceDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameRegistrationDto;
import reactor.core.publisher.Mono;

@RequestMapping("/api/game-service/game")
public interface GameApi {

  @PostMapping("/register")
  Mono<Void> registerGame(
      @RequestBody GameRegistrationDto gameRegistration);

  @PostMapping("/enter")
  Mono<Void> enterGame(
    @RequestBody GameEntranceDto gameEntrance);
}
