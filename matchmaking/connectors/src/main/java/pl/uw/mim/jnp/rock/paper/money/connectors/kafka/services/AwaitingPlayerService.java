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
  private WebClient webClient;
  @Value("#{${game.service.registration.uri}}")
  private String registrationApiUri;


  public void handleAwaitingPlayer(String playerUsername, Integer stake) {
    System.out.println("Starting handling player " + playerUsername + " at stake " + stake);
    if (redisAwaitingPlayerRepository.isPlayerAwaitingOnStake(stake)) {
      System.out.println("A player was already waiting at the stake");
      registerNewGame(playerUsername, redisAwaitingPlayerRepository.getAwaitingPlayerUsername(stake), stake);
    } else {
      System.out.println("No player was waiting at the stake. Saving player");
      redisAwaitingPlayerRepository.saveAwaitingPlayer(playerUsername, stake);
      System.out.println("Player saved. Check: " + redisAwaitingPlayerRepository.isPlayerAwaitingOnStake(stake));
    }
  }
  private void postGameRegistration(GameRegistrationDto gameRegistrationDto)
  {
    System.out.println(webClient.toString());
    webClient.post()
        .uri(registrationApiUri)
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
    System.out.println("Registering new game"+ registrationDto.toString());
    postGameRegistration(registrationDto);
  }


}
