package pl.uw.mim.jnp.rock.paper.money.app.usecases.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.GameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.GameHistoryService;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class PostHistory {

  private final GameHistoryService gameHistoryService;

  public Mono<Void> execute(GameHistoryDto gameHistory) {
    return gameHistoryService.postGameHistory(gameHistory);
  }
}
