package pl.uw.mim.jnp.rock.paper.money.app.usecases.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.exceptions.InvalidGameException;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameEntranceDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.GameService;
import pl.uw.mim.jnp.rock.paper.money.app.services.NotificationService;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class EnterGame {

  private final GameService gameService;
  private final NotificationService notificationService;

  public Mono<Void> execute(GameEntranceDto gameEntrance) {
    enterGameOrThrow(gameEntrance);
    checkGameStatusAndSendNotificationIfFinished(gameEntrance);

    return Mono.empty();
  }

  private void enterGameOrThrow(GameEntranceDto gameEntrance) {
    boolean gameEntranceResult =
        gameService.enterGame(
            gameEntrance.getGameId(), gameEntrance.getPlayerId(), gameEntrance.getHandSign());

    if (!gameEntranceResult) {
      throw new InvalidGameException();
    }
  }

  private void checkGameStatusAndSendNotificationIfFinished(GameEntranceDto gameEntrance) {
    Long gameId = gameEntrance.getGameId();

    Long player1Id = gameService.getPlayer1Id(gameId);
    Long player2Id = gameService.getPlayer2Id(gameId);
    GameStatus gameStatus = gameService.getGameStatus(gameId);
    Integer stake = gameService.getStake(gameId);

    if (!gameStatus.equals(GameStatus.IN_PROGRESS)) {
      notificationService.notifyPlayersAboutResult(gameId, player1Id, player2Id, gameStatus, stake);
    }
  }
}
