package pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.LastGameIdEntity;

@Repository
public interface LastGameIdRepository extends CrudRepository<LastGameIdEntity, Long> {}
