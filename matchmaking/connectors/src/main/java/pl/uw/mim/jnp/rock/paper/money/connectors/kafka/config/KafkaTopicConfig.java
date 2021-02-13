
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

  private final KafkaAwaitingPlayerTopicsProperties kafkaAwaitingPlayerTopicsProperties;

  @Bean
  public NewTopic awaitingPlayerTopic1() {
    return new NewTopic(kafkaAwaitingPlayerTopicsProperties.getTopics().get("topic1"), 1, (short) 1);
  }
  @Bean
  public NewTopic awaitingPlayerTopic2() {
    return new NewTopic(kafkaAwaitingPlayerTopicsProperties.getTopics().get("topic2"), 1, (short) 1);
  }
  @Bean
  public NewTopic awaitingPlayerTopic3() {
    return new NewTopic(kafkaAwaitingPlayerTopicsProperties.getTopics().get("topic3"), 1, (short) 1);
  }
  @Bean
  public NewTopic awaitingPlayerTopic4() {
    return new NewTopic(kafkaAwaitingPlayerTopicsProperties.getTopics().get("topic4"), 1, (short) 1);
  }
  @Bean
  public NewTopic awaitingPlayerTopic5() {
    return new NewTopic(kafkaAwaitingPlayerTopicsProperties.getTopics().get("topic5"), 1, (short) 1);
  }
}
