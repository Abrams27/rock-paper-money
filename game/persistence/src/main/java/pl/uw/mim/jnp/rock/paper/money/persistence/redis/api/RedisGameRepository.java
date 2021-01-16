package pl.uw.mim.jnp.rock.paper.money.persistence.redis.api;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.GameEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.PlayerMoveEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo.GameRepository;

@Component
@AllArgsConstructor
public class RedisGameRepository {

  private final GameRepository gameRepository;

  public void saveGameEntrance(Long gameId, Long player1Id, Long player2Id, Integer stake) {
    GameEntity gameEntity = getGameEntity(gameId, player1Id, player2Id, stake);

    gameRepository.save(gameEntity);
  }

  private GameEntity getGameEntity(Long gameId, Long player1Id, Long player2Id, Integer stake) {
    PlayerMoveEntity player1MoveEntity = getPlayerMoveEntity(player1Id);
    PlayerMoveEntity player2MoveEntity = getPlayerMoveEntity(player2Id);

    return GameEntity.builder()
        .id(gameId)
        .player1Move(player1MoveEntity)
        .player2Move(player2MoveEntity)
        .stake(stake)
        .build();
  }

  private PlayerMoveEntity getPlayerMoveEntity(Long playerId) {
    return PlayerMoveEntity.builder()
        .playerId(playerId)
        .handSign(Optional.empty())
        .build();
  }
}
