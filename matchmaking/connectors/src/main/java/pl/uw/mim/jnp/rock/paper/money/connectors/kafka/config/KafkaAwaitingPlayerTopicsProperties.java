package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.config;

import java.util.Map;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "kafka.awaiting.player")
@Data
public class KafkaAwaitingPlayerTopicsProperties {

  private Map<String, Integer> stakes;
  private Map<String, String> topics;
}
