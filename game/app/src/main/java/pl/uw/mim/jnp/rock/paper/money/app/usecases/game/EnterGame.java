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
            gameEntrance.getGameId(), gameEntrance.getPlayerUsername(), gameEntrance.getHandSign());

    if (!gameEntranceResult) {
      throw new InvalidGameException();
    }
  }

  private Mono<Void> checkGameStatusAndCheckIfGameEnded(GameEntranceDto gameEntrance) {
    Long gameId = gameEntrance.getGameId();

    String player1Username = gameService.getPlayer1Username(gameId);
    String player2Username = gameService.getPlayer2Username(gameId);
    GameStatus gameStatus = gameService.getGameStatus(gameId);
    Integer stake = gameService.getStake(gameId);

    PlayersNotification playersNotification = createPlayersNotification(gameId, player1Username, player2Username, gameStatus, stake);

    return notifyPlayersAndUpdateGameHistories(playersNotification);
  }

  private PlayersNotification createPlayersNotification(Long gameId, String player1, String player2, GameStatus gameStatus, Integer stake) {
    return PlayersNotification.builder()
        .gameId(gameId)
        .player1(player1)
        .player2(player2)
        .gameStatus(gameStatus)
        .stake(stake)
        .build();
  }

  private Mono<Void> notifyPlayersAndUpdateGameHistories(PlayersNotification playersNotification) {
    Mono<Void> kafkaNotification = Mono.just(playersNotification)
        .filter(notification -> !notification.getGameStatus().equals(GameStatus.IN_PROGRESS))
        .flatMap(notificationService::notifyPlayersAboutResult);

    Mono<Void> userServiceCall = Mono.just(playersNotification)
        .filter(notification -> !notification.getGameStatus().equals(GameStatus.IN_PROGRESS))
        .flatMap(notificationService::updatePlayersGameHistories);

    return Mono.zip(kafkaNotification, userServiceCall)
        .then();
  }
}
