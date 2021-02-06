package pl.uw.mim.jnp.rock.paper.money.app.usecases.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.GameHistoryDto;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PostHistory {

  public Mono<Void> execute(GameHistoryDto gameHistory) {
    return Mono.empty();
  }
}
