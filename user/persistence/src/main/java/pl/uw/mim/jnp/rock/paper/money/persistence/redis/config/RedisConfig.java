package pl.uw.mim.jnp.rock.paper.money.persistence.redis.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

@Configuration
@ComponentScan(basePackages = "pl.uw.mim.jnp.rock.paper.money.persistence.redis")
@EnableRedisRepositories(basePackages = "pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo")
@AllArgsConstructor
public class RedisConfig {

  private final RedisConfigProperties redisConfigProperties;

  @Bean
  JedisConnectionFactory jedisConnectionFactory() {
    JedisConnectionFactory jedisConFactory
        = new JedisConnectionFactory();
    jedisConFactory.setHostName(redisConfigProperties.getHost());
    jedisConFactory.setPort(redisConfigProperties.getPort());
    return jedisConFactory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    final RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(jedisConnectionFactory());
    template.setValueSerializer(new GenericToStringSerializer<>(Object.class));

    return template;
  }
}
