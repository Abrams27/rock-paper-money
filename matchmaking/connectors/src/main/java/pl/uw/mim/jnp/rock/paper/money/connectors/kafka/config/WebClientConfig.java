package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class WebClientConfig {

  @Bean
  public WebClient webClient() {
    return WebClient.create("http://game:8080/api/game-service/game");
  }
}
