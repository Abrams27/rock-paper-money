package pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.creators;

import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.PlayerMoveEntity;

@UtilityClass
public class PlayerMoveEntityCreator {

  public PlayerMoveEntity from(String username) {
    return PlayerMoveEntity.builder()
        .playerUsername(username)
        .handSign(null)
        .build();
  }
}
