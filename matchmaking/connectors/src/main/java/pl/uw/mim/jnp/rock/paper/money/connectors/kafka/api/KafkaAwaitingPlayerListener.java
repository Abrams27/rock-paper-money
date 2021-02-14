package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.config.KafkaAwaitingPlayerTopicsProperties;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.services.AwaitingPlayerService;

@Component
public class KafkaAwaitingPlayerListener {

  @Autowired
  private KafkaAwaitingPlayerTopicsProperties kafkaAwaitingPlayerTopicsProperties;

  @Autowired
  private AwaitingPlayerService awaitingPlayerService;

  @KafkaListener(topics = "awaiting.player.stake.1")
  void topic1Listener(String playerUsername) {
    System.out.println(playerUsername + "applied to play the game at stake ");
//    System.out.println(kafkaAwaitingPlayerTopicsProperties.getStakes().get("topic1"));
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
