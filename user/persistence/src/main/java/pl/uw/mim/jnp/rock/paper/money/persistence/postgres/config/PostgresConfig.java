package pl.uw.mim.jnp.rock.paper.money.persistence.postgres.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(basePackages = "pl.uw.mim.jnp.rock.paper.money.persistence.postgres")
@EntityScan("pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries")
@EnableJpaRepositories("pl.uw.mim.jnp.rock.paper.money.persistence.postgres.repo")
public class PostgresConfig {

}
