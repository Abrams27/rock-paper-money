package pl.uw.mim.jnp.rock.paper.money.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

@SpringBootApplication(
    exclude = { RedisAutoConfiguration.class, RedisRepositoriesAutoConfiguration.class }
)
public class MatchmakingApplication {

  public static void main(String[] args) {
    SpringApplication.run(MatchmakingApplication.class, args);
  }
}
