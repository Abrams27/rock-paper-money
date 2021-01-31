package pl.uw.mim.jnp.rock.paper.money.app.usecases.game;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.exceptions.InvalidGameException;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameEntranceDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.GameService;
import pl.uw.mim.jnp.rock.paper.money.app.services.NotificationService;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.PlayersNotification;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class EnterGame {

  private final GameService gameService;
  private final NotificationService notificationService;

  public Mono<Void> execute(GameEntranceDto gameEntrance) {
    return Mono.just(gameEntrance)
        .doOnNext(this::enterGameOrThrow)
        .flatMap(this::checkGameStatusAndCheckIfGameEnded);
  }

  private void enterGameOrThrow(GameEntranceDto gameEntrance) {
    boolean gameEntranceResult =
        gameService.enterGame(
            gameEntrance.getGameId(), gameEntrance.getPlayerId(), gameEntrance.getHandSign());

    if (!gameEntranceResult) {
      throw new InvalidGameException();
    }
  }

  private Mono<Void> checkGameStatusAndCheckIfGameEnded(GameEntranceDto gameEntrance) {
    Long gameId = gameEntrance.getGameId();

    Long player1Id = gameService.getPlayer1Id(gameId);
    Long player2Id = gameService.getPlayer2Id(gameId);
    GameStatus gameStatus = gameService.getGameStatus(gameId);
    Integer stake = gameService.getStake(gameId);

    PlayersNotification playersNotification = createPlayersNotification(gameId, player1Id, player2Id, gameStatus, stake);

    return Mono.just(playersNotification)
        .filter(notification -> !notification.getGameStatus().equals(GameStatus.IN_PROGRESS))
        .flatMap(notificationService::notifyPlayersAboutResult);
  }

  private PlayersNotification createPlayersNotification(Long gameId, Long player1, Long player2, GameStatus gameStatus, Integer stake) {
    return PlayersNotification.builder()
        .gameId(gameId)
        .player1(player1)
        .player2(player2)
        .gameStatus(gameStatus)
        .stake(stake)
        .build();
  }
}
