package pl.uw.mim.jnp.rock.paper.money.persistence.redis.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.AwaitingPlayerEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.creators.AwaitingPlayerEntityCreator;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo.AwaitingPlayerRepository;

@Component
@AllArgsConstructor
public class RedisAwaitingPlayerRepository {

  private final AwaitingPlayerRepository awaitingPlayerRepository;

  public void saveAwaitingPlayer(String playerUsername, Integer stake){
    AwaitingPlayerEntity awaitingPlayerEntity = AwaitingPlayerEntityCreator.from(playerUsername, stake);
    awaitingPlayerRepository.save(awaitingPlayerEntity);
  }

  public String getAwaitingPlayerUsername(Integer stake){
    var awaitingPlayer = awaitingPlayerRepository.findByStake(stake).orElseThrow();
    awaitingPlayerRepository.deleteById(awaitingPlayer.getId());
    return awaitingPlayer.getPlayerUsername();
  }

  public boolean isPlayerAwaitingOnStake(Integer stake){
    return awaitingPlayerRepository.findByStake(stake).isPresent();
  }
}
