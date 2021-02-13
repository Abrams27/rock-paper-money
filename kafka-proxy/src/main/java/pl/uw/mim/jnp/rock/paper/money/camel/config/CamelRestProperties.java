package pl.uw.mim.jnp.rock.paper.money.camel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "camel.config.rest")
@Data
public class CamelRestProperties {

  private String endpoint;
  private Integer port;
}
