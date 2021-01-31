package pl.uw.mim.jnp.rock.paper.money.app.usecases.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.GameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.GameHistoryService;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class GetGamesHistory {

  private final GameHistoryService gameHistoryService;

  public Flux<GameHistoryDto> execute(Long userId) {
    return gameHistoryService.getHistoryForUser(userId);
  }

}
