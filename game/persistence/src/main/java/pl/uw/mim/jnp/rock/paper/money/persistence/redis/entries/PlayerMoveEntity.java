package pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries;

import java.io.Serializable;
import java.util.Optional;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayerMoveEntity implements Serializable {

  private Long playerId;

  private Optional<HandSign> handSign;
}
