package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.PlayersNotification;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.KafkaNotificationSender;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.models.GameResult;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.models.GameResultNotification;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class NotificationService {

  private final KafkaNotificationSender kafkaNotificationSender;

  public Mono<Void> notifyPlayersAboutResult(PlayersNotification playersNotification) {
    return Mono.just(playersNotification)
        .map(this::createMessage)
        .flatMap(kafkaNotificationSender::sendNotification);
  }

  private GameResultNotification createMessage(PlayersNotification playersNotification) {
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
}
