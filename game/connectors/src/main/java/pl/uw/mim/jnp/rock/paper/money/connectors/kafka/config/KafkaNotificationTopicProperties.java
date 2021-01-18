package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.notification")
@Data
public class KafkaNotificationTopicProperties {

  private String topic;

}
