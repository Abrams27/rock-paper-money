package pl.uw.mim.jnp.rock.paper.money.camel.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "camel.common")
@Data
public class CamelCommonProperties {

  private Integer port;

  private Integer stake1;
  private Integer stake2;
  private Integer stake3;
  private Integer stake4;
  private Integer stake5;
}
