package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.KafkaNotificationSender;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.models.GameResult;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.models.GameResultNotification;

@Service
@AllArgsConstructor
public class NotificationService {

  private final KafkaNotificationSender kafkaNotificationSender;

  public void notifyPlayersAboutResult(Long gameId, Long player1, Long player2, GameStatus gameStatus, Integer stake) {
    GameResultNotification notification = createMessage(gameId, player1, player2, gameStatus, stake);
    kafkaNotificationSender.sendNotification(notification);
  }

  private GameResultNotification createMessage(Long gameId, Long player1, Long player2, GameStatus gameStatus, Integer stake) {
    return GameResultNotification.builder()
        .gameId(gameId)
        .player1(player1)
        .player2(player2)
        .stake(stake)
        .gameResult(getGameResultForGameStatus(gameStatus))
        .build();
  }

  private GameResult getGameResultForGameStatus(GameStatus gameStatus) {
    return GameResult.valueOf(gameStatus.toString());
  }
}
