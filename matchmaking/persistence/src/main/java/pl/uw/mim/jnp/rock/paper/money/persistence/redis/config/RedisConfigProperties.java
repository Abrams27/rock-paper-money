package pl.uw.mim.jnp.rock.paper.money.persistence.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "redis.config")
@Data
class RedisConfigProperties {

  private String host;
  private Integer port;

}
