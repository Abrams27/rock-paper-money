package pl.uw.mim.jnp.rock.paper.money.connectors.user.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user.service")
@Data
public class UserServiceClientProperties {

  private String host;
  private Integer port;

  private Endpoint endpoint;

  @Data
  public static class Endpoint {

    private String history;
  }
}
