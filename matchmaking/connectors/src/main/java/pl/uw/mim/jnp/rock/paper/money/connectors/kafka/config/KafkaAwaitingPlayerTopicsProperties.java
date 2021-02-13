package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.config;

import java.util.Map;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//@ConfigurationProperties(prefix = "kafka.awaiting.player")
@Component
@Data
public class KafkaAwaitingPlayerTopicsProperties {

  @Value("#{${kafka.awaiting.player.stakes}}")
  private Map<String, Integer> stakes;
  @Value("#{${kafka.awaiting.player.topics}}")
  private Map<String, String> topics;
}
