package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.api.RedisAwaitingPlayerRepository;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.api.RedisLastGameIdRepository;

@Service
@AllArgsConstructor
public class AwaitingPlayerService {

  private RedisAwaitingPlayerRepository redisAwaitingPlayerRepository;
  private RedisLastGameIdRepository lastGameIdRepository;

  public void handleAwaitingPlayer(String playerUsername, Integer stake) {
    if (redisAwaitingPlayerRepository.isPlayerAwaitingOnStake(stake)) {
      registerNewGame(playerUsername, redisAwaitingPlayerRepository.getAwaitingPlayerUsername(stake), stake);
    } else {
      redisAwaitingPlayerRepository.saveAwaitingPlayer(playerUsername, stake);
    }
  }

  private void registerNewGame(String playerUsername1, String playerUsername2, Integer stake) {
    System.out.println(
        "We are about to register game: " + playerUsername1 + " vs " + playerUsername2 + ". The stake is " + stake);
  }


}
