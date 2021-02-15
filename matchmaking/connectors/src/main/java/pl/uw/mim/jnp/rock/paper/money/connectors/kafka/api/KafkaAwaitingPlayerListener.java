package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api;

import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.services.AwaitingPlayerService;

@Component
@AllArgsConstructor
public class KafkaAwaitingPlayerListener {

  private final AwaitingPlayerService awaitingPlayerService;

  @KafkaListener(topics = "awaiting.player.stake.1")
  void topic1Listener(String playerUsername) {
    awaitingPlayerService
        .handleAwaitingPlayer(playerUsername, 1);
  }

  @KafkaListener(topics = "awaiting.player.stake.2")
  void topic2Listener(String playerUsername) {
    awaitingPlayerService
        .handleAwaitingPlayer(playerUsername, 2);
  }

  @KafkaListener(topics = "awaiting.player.stake.5")
  void topic3Listener(String playerUsername) {
    awaitingPlayerService
        .handleAwaitingPlayer(playerUsername, 5);
  }

  @KafkaListener(topics = "awaiting.player.stake.10")
  void topic4Listener(String playerUsername) {
    awaitingPlayerService
        .handleAwaitingPlayer(playerUsername, 10);
  }

  @KafkaListener(topics = "awaiting.player.stake.20")
  void topic5Listener(String playerUsername) {
    awaitingPlayerService
        .handleAwaitingPlayer(playerUsername, 20);
  }
}
