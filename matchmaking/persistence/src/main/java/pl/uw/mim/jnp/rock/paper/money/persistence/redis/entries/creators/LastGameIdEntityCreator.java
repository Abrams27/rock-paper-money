package pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.creators;

import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.LastGameIdEntity;

@UtilityClass
public class LastGameIdEntityCreator {
  public LastGameIdEntity from(Long lastGameId){
    return LastGameIdEntity.builder()
        .gameId(lastGameId)
        .build();
  }
}
