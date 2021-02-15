package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.PlayersNotification;
import pl.uw.mim.jnp.rock.paper.money.connectors.user.api.UserServiceClient;
import pl.uw.mim.jnp.rock.paper.money.connectors.user.api.models.GameHistory;
import pl.uw.mim.jnp.rock.paper.money.connectors.user.api.models.GameResult;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class NotificationService {

  private final UserServiceClient userServiceClient;

  public Mono<Void> updatePlayersGameHistories(PlayersNotification playersNotification) {
    return Mono.just(playersNotification)
        .map(this::creteGameHistoryMessage)
        .flatMap(userServiceClient::postGameHistory);
  }

  private GameHistory creteGameHistoryMessage(PlayersNotification playersNotification) {
    return GameHistory.builder()
        .username1(playersNotification.getPlayer1())
        .username2(playersNotification.getPlayer2())
        .gameResult(getUserGameResultForGameStatus(playersNotification.getGameStatus()))
        .stake(playersNotification.getStake())
        .build();
  }

  private GameResult getUserGameResultForGameStatus(GameStatus gameStatus) {
    return GameResult.valueOf(gameStatus.toString());
  }
}
