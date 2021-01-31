package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api;

import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.models.GameResultNotification;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.config.KafkaNotificationTopicProperties;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class KafkaNotificationSender {

  private final KafkaTemplate<String, GameResultNotification> kafkaTemplate;
  private final KafkaNotificationTopicProperties kafkaNotificationTopicProperties;

  public Mono<Void> sendNotification(GameResultNotification gameResultNotification) {
    return Mono
        .fromFuture(kafkaTemplate.send(kafkaNotificationTopicProperties.getTopic(), gameResultNotification).completable())
        .then();
  }
}
