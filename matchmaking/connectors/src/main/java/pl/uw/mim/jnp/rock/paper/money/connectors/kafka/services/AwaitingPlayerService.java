package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.services;


import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.models.GameRegistrationDto;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.api.RedisAwaitingPlayerRepository;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.api.RedisLastGameIdRepository;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AwaitingPlayerService {

  private RedisAwaitingPlayerRepository redisAwaitingPlayerRepository;
  private RedisLastGameIdRepository redisLastGameIdRepository;

  private final WebClient webClient;

//  @Value("#{${game.service.registration.uri}}")
//  private final String registrationApiUri;


  public void handleAwaitingPlayer(String playerUsername, Integer stake) {
    if (redisAwaitingPlayerRepository.isPlayerAwaitingOnStake(stake)) {
      registerNewGame(playerUsername, redisAwaitingPlayerRepository.getAwaitingPlayerUsername(stake), stake);
    } else {
      redisAwaitingPlayerRepository.saveAwaitingPlayer(playerUsername, stake);
    }
  }
  private void postGameRegistration(GameRegistrationDto gameRegistrationDto)
  {
    System.out.println("register: "+ gameRegistrationDto.toString() + webClient.get().toString());
    webClient.post()
        .uri("/register")
        .body(Mono.just(gameRegistrationDto), GameRegistrationDto.class)
        .retrieve()
        .bodyToMono(Void.class);
  }


  private void registerNewGame(String playerUsername1, String playerUsername2, Integer stake) {
    Long nextGameId = redisLastGameIdRepository.getNextGameId();
    GameRegistrationDto registrationDto = GameRegistrationDto.builder()
        .gameId(nextGameId)
        .player1Username(playerUsername1)
        .player2Username(playerUsername2)
        .stake(stake)
        .build();
    postGameRegistration(registrationDto);
  }


}
