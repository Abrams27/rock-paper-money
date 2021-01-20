package pl.uw.mim.jnp.rock.paper.money.persistence.postgres.repo;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameHistoryEntity;

@Repository
public interface GameHistoryRepository extends JpaRepository<GameHistoryEntity, Long> {

  List<GameHistoryEntity> findAllByPlayer1IdOrPlayer2Id(Long player1Id, Long player2Id);
}
