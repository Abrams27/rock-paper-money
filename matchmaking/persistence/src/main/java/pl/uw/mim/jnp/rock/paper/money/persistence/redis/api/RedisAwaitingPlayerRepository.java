package pl.uw.mim.jnp.rock.paper.money.persistence.redis.api;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.AwaitingPlayerEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.creators.AwaitingPlayerEntityCreator;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo.AwaitingPlayerRepository;

@Component
@AllArgsConstructor
public class RedisAwaitingPlayerRepository {

  private final AwaitingPlayerRepository awaitingPlayerRepository;

  public void saveAwaitingPlayer(Long playerId, Integer stake){
    AwaitingPlayerEntity awaitingPlayerEntity = AwaitingPlayerEntityCreator.from(playerId, stake);
    awaitingPlayerRepository.save(awaitingPlayerEntity);
  }

  public Optional<Long> getAwaitingPlayerId(Integer stake){
    return awaitingPlayerRepository.findByStake(stake)
        .map(AwaitingPlayerEntity::getPlayerId);
  }
}
