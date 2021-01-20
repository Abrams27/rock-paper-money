package pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.creators;

import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.GameEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.PlayerMoveEntity;

@UtilityClass
public class GameEntityCreator {

  public GameEntity from(Long gameId, Long player1Id, Long player2Id, Integer stake) {
    PlayerMoveEntity player1MoveEntity = PlayerMoveEntityCreator.from(player1Id);
    PlayerMoveEntity player2MoveEntity = PlayerMoveEntityCreator.from(player2Id);

    return GameEntity.builder()
        .id(gameId)
        .player1Move(player1MoveEntity)
        .player2Move(player2MoveEntity)
        .stake(stake)
        .build();
  }

}
