package pl.uw.mim.jnp.rock.paper.money.camel;

import lombok.AllArgsConstructor;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.camel.config.CamelCommonProperties;
import pl.uw.mim.jnp.rock.paper.money.camel.routes.CamelKafkaRoute;
import pl.uw.mim.jnp.rock.paper.money.camel.routes.CamelRestRoute;

@Component
@AllArgsConstructor
public class RestToKafkaRouteBuilder extends RouteBuilder {

  private final CamelRestRoute camelRestRoute;
  private final CamelKafkaRoute camelKafkaRoute;

  private final CamelCommonProperties camelCommonProperties;

  @Override
  public void configure() {
    addRouteForStake(camelCommonProperties.getStake1());
    addRouteForStake(camelCommonProperties.getStake2());
    addRouteForStake(camelCommonProperties.getStake3());
    addRouteForStake(camelCommonProperties.getStake4());
    addRouteForStake(camelCommonProperties.getStake5());
  }

  private void addRouteForStake(Integer stake) {
    from(camelRestRoute.getRoute(stake))
        .to(camelKafkaRoute.getRoute(stake));
  }
}
