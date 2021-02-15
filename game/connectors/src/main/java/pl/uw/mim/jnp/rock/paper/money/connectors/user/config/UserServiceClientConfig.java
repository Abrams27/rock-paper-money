package pl.uw.mim.jnp.rock.paper.money.connectors.user.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@ComponentScan(basePackages = "pl.uw.mim.jnp.rock.paper.money.connectors.user")
@AllArgsConstructor
public class UserServiceClientConfig {

  private final UserServiceClientProperties userServiceClientProperties;

  @Bean
  public WebClient webClient() {
    return WebClient.create(getBaseUserServiceUrl());
  }

  private String getBaseUserServiceUrl() {
    return String.format("http://%s:%d", userServiceClientProperties.getHost(), userServiceClientProperties.getPort());
  }
}
