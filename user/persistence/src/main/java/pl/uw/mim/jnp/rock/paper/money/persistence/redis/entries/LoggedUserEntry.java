package pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries;

import java.io.Serializable;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("LoggedUserEntry")
@Data
@Builder
public class LoggedUserEntry implements Serializable  {

  private String id;
}
