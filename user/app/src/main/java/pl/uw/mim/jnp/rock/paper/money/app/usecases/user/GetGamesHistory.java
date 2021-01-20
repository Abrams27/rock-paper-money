package pl.uw.mim.jnp.rock.paper.money.app.usecases.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.GameHistoryDto;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class GetGamesHistory {

  public Flux<GameHistoryDto> execute(Long userIdd) {
    // TODO
    return Flux.empty();
  }

}
