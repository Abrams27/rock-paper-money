package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.PlayersNotification;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.KafkaNotificationSender;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.models.GameResult;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.models.GameResultNotification;
import pl.uw.mim.jnp.rock.paper.money.connectors.user.api.UserServiceClient;
import pl.uw.mim.jnp.rock.paper.money.connectors.user.api.models.GameHistory;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class NotificationService {

  private final KafkaNotificationSender kafkaNotificationSender;
  private final UserServiceClient userServiceClient;

  public Mono<Void> notifyPlayersAboutResult(PlayersNotification playersNotification) {
    return Mono.just(playersNotification)
        .map(this::createKafkaMessage)
        .flatMap(kafkaNotificationSender::sendNotification);
  }

  private GameResultNotification createKafkaMessage(PlayersNotification playersNotification) {
    return GameResultNotification.builder()
        .gameId(playersNotification.getGameId())
        .player1(playersNotification.getPlayer1())
        .player2(playersNotification.getPlayer2())
        .stake(playersNotification.getStake())
        .gameResult(getGameResultForGameStatus(playersNotification.getGameStatus()))
        .build();
  }

  private GameResult getGameResultForGameStatus(GameStatus gameStatus) {
    return GameResult.valueOf(gameStatus.toString());
  }

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

  private pl.uw.mim.jnp.rock.paper.money.connectors.user.api.models.GameResult getUserGameResultForGameStatus(GameStatus gameStatus) {
    return pl.uw.mim.jnp.rock.paper.money.connectors.user.api.models.GameResult.valueOf(gameStatus.toString());
  }
}
