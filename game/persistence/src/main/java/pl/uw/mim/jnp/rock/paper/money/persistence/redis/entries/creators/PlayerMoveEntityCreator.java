package pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.creators;

import java.util.Optional;
import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.PlayerMoveEntity;

@UtilityClass
public class PlayerMoveEntityCreator {

  public PlayerMoveEntity from(Long playerId) {
    return PlayerMoveEntity.builder()
        .playerId(playerId)
        .handSign(Optional.empty())
        .build();
  }
}
