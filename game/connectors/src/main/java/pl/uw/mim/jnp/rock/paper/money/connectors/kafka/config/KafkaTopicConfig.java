package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.config;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "pl.uw.mim.jnp.rock.paper.money.connectors.kafka")
@AllArgsConstructor
public class KafkaTopicConfig {

  private final KafkaNotificationTopicProperties kafkaNotificationTopicProperties;

  @Bean
  public NewTopic notificationTopic() {
    return new NewTopic(kafkaNotificationTopicProperties.getTopic(), 1, (short) 1);
  }
}
