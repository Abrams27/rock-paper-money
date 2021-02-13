package pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash("AwaitingPlayerEntity")
public class AwaitingPlayerEntity {
  private Long id;

  private String playerUsername;

  private Integer stake;
}
