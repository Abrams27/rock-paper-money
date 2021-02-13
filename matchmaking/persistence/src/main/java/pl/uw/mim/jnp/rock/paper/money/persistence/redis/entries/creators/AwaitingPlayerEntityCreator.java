package pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.creators;

import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.AwaitingPlayerEntity;

@UtilityClass
public class AwaitingPlayerEntityCreator {
  public AwaitingPlayerEntity from(Long playerId, Integer stake){
    return AwaitingPlayerEntity.builder()
        .playerId(playerId)
        .stake(stake)
        .build();
  }
}
