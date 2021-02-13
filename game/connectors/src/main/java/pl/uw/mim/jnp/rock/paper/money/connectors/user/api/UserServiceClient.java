package pl.uw.mim.jnp.rock.paper.money.connectors.user.api;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import pl.uw.mim.jnp.rock.paper.money.connectors.user.api.models.GameHistory;
import pl.uw.mim.jnp.rock.paper.money.connectors.user.config.UserServiceClientProperties;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class UserServiceClient {

  private final WebClient webClient;
  private final UserServiceClientProperties userServiceClientProperties;

  public Mono<Void> postGameHistory(GameHistory gameHistory) {
    return webClient.post()
        .uri(userServiceClientProperties.getEndpoint().getHistory())
        .body(Mono.just(gameHistory), GameHistory.class)
        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .exchange()
        .then();
  }
}
