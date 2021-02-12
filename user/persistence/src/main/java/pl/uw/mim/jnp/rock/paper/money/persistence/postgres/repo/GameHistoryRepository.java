package pl.uw.mim.jnp.rock.paper.money.persistence.postgres.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameHistoryEntity;

@Repository
public interface GameHistoryRepository extends JpaRepository<GameHistoryEntity, Long> { }
