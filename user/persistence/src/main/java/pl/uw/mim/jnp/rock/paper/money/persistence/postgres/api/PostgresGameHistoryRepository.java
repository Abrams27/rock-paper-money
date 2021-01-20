package pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameHistoryEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.repo.GameHistoryRepository;

@Component
@AllArgsConstructor
public class PostgresGameHistoryRepository {

  private final GameHistoryRepository gameHistoryRepository;

  public List<GameHistoryEntity> getHistoryForUserId(Long userId) {
    return gameHistoryRepository.findAllByPlayer1IdOrPlayer2Id(userId, userId);
  }
}
