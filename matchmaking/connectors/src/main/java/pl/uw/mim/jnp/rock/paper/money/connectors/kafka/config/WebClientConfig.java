package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.config;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@AllArgsConstructor
public class WebClientConfig {

  @Value("#{${game.service.base.url}}")
  private final String baseGameServiceUrl;

  @Bean
  public WebClient webClient() {
    return WebClient.builder().baseUrl(baseGameServiceUrl).build();
  }

}
