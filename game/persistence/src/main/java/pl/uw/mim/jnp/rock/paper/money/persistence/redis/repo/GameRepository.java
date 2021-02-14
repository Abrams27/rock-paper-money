package pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.GameEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<GameEntity, Long> {

  List<GameEntity> findAll();
}
