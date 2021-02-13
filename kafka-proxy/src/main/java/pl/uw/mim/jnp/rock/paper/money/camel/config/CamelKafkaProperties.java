package pl.uw.mim.jnp.rock.paper.money.camel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "camel.kafka")
@Data
public class CamelKafkaProperties {

  private String topicprefix;
  private String url;
}
