package pl.uw.mim.jnp.rock.paper.money.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "user.starting")
@Data
public class UserProperties {

  private Integer balance;
}
