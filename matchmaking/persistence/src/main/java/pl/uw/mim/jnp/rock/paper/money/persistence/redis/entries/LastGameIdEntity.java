package pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@Data
@Builder
@RedisHash("LastGameIdEntity")
public class LastGameIdEntity {
  private Long id;
  private Long gameId;
}
