package pl.uw.mim.jnp.rock.paper.money.camel.routes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.camel.config.CamelKafkaProperties;

@Component
@AllArgsConstructor
public class CamelKafkaRoute {

  private final CamelKafkaProperties camelKafkaProperties;

  public String getRoute(Integer stake) {
    return String.format("kafka:%s.%d?brokers=%s",
        camelKafkaProperties.getTopicprefix(),
        stake,
        camelKafkaProperties.getUrl());
  }
}
