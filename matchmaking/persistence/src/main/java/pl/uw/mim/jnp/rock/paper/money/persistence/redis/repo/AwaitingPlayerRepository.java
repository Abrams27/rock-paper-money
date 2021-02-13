package pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.AwaitingPlayerEntity;

@Repository
public interface AwaitingPlayerRepository extends CrudRepository<AwaitingPlayerEntity, Long> {

  Optional<AwaitingPlayerEntity> findByStake(Integer stake);
}
