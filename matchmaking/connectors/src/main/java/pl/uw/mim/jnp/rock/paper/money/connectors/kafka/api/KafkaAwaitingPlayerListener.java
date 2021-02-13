package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.config.KafkaAwaitingPlayerTopicsProperties;

@Component
public class KafkaAwaitingPlayerListener {

  private KafkaAwaitingPlayerTopicsProperties kafkaAwaitingPlayerTopicsProperties;

  @KafkaListener(topics = "awaiting.player.stake.1")
  void topic1Listener(String playerUsername){
    System.out.println(playerUsername);
  }
  @KafkaListener(topics = "awaiting.player.stake.2")
  void topic2Listener(String playerUsername){
    System.out.println(playerUsername);
  }
  @KafkaListener(topics = "awaiting.player.stake.5")
  void topic3Listener(String playerUsername){
    System.out.println(playerUsername);
  }
  @KafkaListener(topics = "awaiting.player.stake.10")
  void topic4Listener(String playerUsername){
    System.out.println(playerUsername);
  }
  @KafkaListener(topics = "awaiting.player.stake.20")
  void topic5Listener(String playerUsername){
    System.out.println(playerUsername);
  }

}
