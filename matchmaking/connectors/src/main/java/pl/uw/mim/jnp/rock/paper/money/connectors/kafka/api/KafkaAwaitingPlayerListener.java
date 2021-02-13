package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.config.KafkaAwaitingPlayerTopicsProperties;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.services.AwaitingPlayerService;

@Component
public class KafkaAwaitingPlayerListener {

  private KafkaAwaitingPlayerTopicsProperties kafkaAwaitingPlayerTopicsProperties;
  private AwaitingPlayerService awaitingPlayerService;

  @KafkaListener(topics = "awaiting.player.stake.1")
  void topic1Listener(String playerUsername) {
    System.out.println(playerUsername + "applied to play the game at stake ");
    System.out.println(kafkaAwaitingPlayerTopicsProperties.getStakes().get("topic1"));

  }

  @KafkaListener(topics = "awaiting.player.stake.2")
  void topic2Listener(String playerUsername) {
    System.out.println(
        playerUsername + "applied to play the game at stake " + kafkaAwaitingPlayerTopicsProperties.getStakes()
            .get("topic2"));
    awaitingPlayerService
        .handleAwaitingPlayer(playerUsername, kafkaAwaitingPlayerTopicsProperties.getStakes().get("topic2"));
  }

  @KafkaListener(topics = "awaiting.player.stake.5")
  void topic3Listener(String playerUsername) {
    System.out.println(
        playerUsername + "applied to play the game at stake " + kafkaAwaitingPlayerTopicsProperties.getStakes()
            .get("topic3"));
    awaitingPlayerService
        .handleAwaitingPlayer(playerUsername, kafkaAwaitingPlayerTopicsProperties.getStakes().get("topic3"));
  }

  @KafkaListener(topics = "awaiting.player.stake.10")
  void topic4Listener(String playerUsername) {
    System.out.println(
        playerUsername + "applied to play the game at stake " + kafkaAwaitingPlayerTopicsProperties.getStakes()
            .get("topic4"));
    awaitingPlayerService
        .handleAwaitingPlayer(playerUsername, kafkaAwaitingPlayerTopicsProperties.getStakes().get("topic4"));
  }

  @KafkaListener(topics = "awaiting.player.stake.20")
  void topic5Listener(String playerUsername) {
    System.out.println(
        playerUsername + "applied to play the game at stake " + kafkaAwaitingPlayerTopicsProperties.getStakes()
            .get("topic5"));
    awaitingPlayerService
        .handleAwaitingPlayer(playerUsername, kafkaAwaitingPlayerTopicsProperties.getStakes().get("topic5"));
  }

}
