package pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("GameEntity")
@Data
@Builder
public class GameEntity implements Serializable {

  private Long id;

  private PlayerMoveEntity player1Move;
  private PlayerMoveEntity player2Move;

  private Integer stake;

  public PlayerMoveEntity getPlayerMove(int playerNumber) {
    if (playerNumber == 1) {
      return player1Move;
    }

    if (playerNumber == 2) {
      return player2Move;
    }

    throw new IllegalArgumentException();
  }

  public void setPlayerMove(PlayerMoveEntity playerMoveEntity, int playerNumber) {
    if (playerNumber == 1) {
      player1Move = playerMoveEntity;
    } else if (playerNumber == 2) {
      player2Move = playerMoveEntity;
    } else {
      throw new IllegalArgumentException();
    }
  }
}
