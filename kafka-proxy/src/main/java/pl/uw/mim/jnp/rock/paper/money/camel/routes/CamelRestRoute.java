package pl.uw.mim.jnp.rock.paper.money.camel.routes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.camel.config.CamelRestProperties;

@Component
@AllArgsConstructor
public class CamelRestRoute {

  private final CamelRestProperties camelRestProperties;

  public String getRoute(Integer stake) {
    return String.format("restlet:http://0.0.0.0:%d%s/%d?restletMethod=post",
        camelRestProperties.getPort(),
        camelRestProperties.getEndpoint(),
        stake);
  }
}
