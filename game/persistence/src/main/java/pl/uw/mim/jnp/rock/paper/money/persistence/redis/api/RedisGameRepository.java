package pl.uw.mim.jnp.rock.paper.money.persistence.redis.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.GameEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.creators.GameEntityCreator;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo.GameRepository;

@Component
@AllArgsConstructor
public class RedisGameRepository {

  private final GameRepository gameRepository;

  public void saveGameEntrance(Long gameId, Long player1Id, Long player2Id, Integer stake) {
    GameEntity gameEntity = GameEntityCreator.from(gameId, player1Id, player2Id, stake);

    gameRepository.save(gameEntity);
  }
}
