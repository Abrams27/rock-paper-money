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
}
