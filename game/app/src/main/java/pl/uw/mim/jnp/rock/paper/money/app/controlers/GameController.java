package pl.uw.mim.jnp.rock.paper.money.app.controlers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.uw.mim.jnp.rock.paper.money.api.endpoints.GameApi;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameEntranceDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameProgress;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameRegistrationDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameResultResponseDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.UserGameResponseDto;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.game.EnterGame;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.game.GameInfo;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.game.GameResult;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.game.NewGame;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.game.RegisterGame;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class GameController implements GameApi {

  private final RegisterGame registerGame;
  private final EnterGame enterGame;
  private final NewGame newGame;
  private final GameInfo gameInfo;
  private final GameResult gameResult;

  @Override
  public Mono<Void> registerGame(GameRegistrationDto gameRegistration) {
    System.out.println("Registering game with id: " + gameRegistration.getGameId());
    return registerGame.execute(gameRegistration);
  }

  @Override
  public Mono<Void> enterGame(GameEntranceDto gameEntrance) {
    return enterGame.execute(gameEntrance);
  }

  @Override
  public Mono<UserGameResponseDto> newGame(String username) {
    return newGame.execute(username);
  }

  @Override
  public Mono<GameProgress> gameInfo(Long gameId) {
    return gameInfo.execute(gameId);
  }

  @Override
  public Mono<GameResultResponseDto> gameResult(Long gameId, String username) {
    return gameResult.execute(gameId, username);
  }
}
