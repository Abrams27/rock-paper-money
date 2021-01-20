package pl.uw.mim.jnp.rock.paper.money.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.uw.mim.jnp.rock.paper.money.api.endpoints.UserApi;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.GameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.user.GetGamesHistory;
import reactor.core.publisher.Flux;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

  private final GetGamesHistory getGamesHistory;

  @Override
  public Flux<GameHistoryDto> getGamesHistory(Long userId) {
    return getGamesHistory.execute(userId);
  }
}
