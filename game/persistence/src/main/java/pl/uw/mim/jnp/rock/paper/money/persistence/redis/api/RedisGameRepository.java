package pl.uw.mim.jnp.rock.paper.money.persistence.redis.api;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.GameEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.HandSign;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.PlayerMoveEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.creators.GameEntityCreator;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo.GameRepository;

@Component
@AllArgsConstructor
public class RedisGameRepository {

  private final GameRepository gameRepository;

  public boolean saveGameEntrance(Long gameId, Long player1Id, Long player2Id, Integer stake) {
    boolean isGameAlreadyRegistered =  gameRepository.findById(gameId).isPresent();

    if (isGameAlreadyRegistered) {
      return false;
    }

    GameEntity gameEntity = GameEntityCreator.from(gameId, player1Id, player2Id, stake);
    gameRepository.save(gameEntity);

    return true;
  }

  public boolean setPlayerMoveIfItIsCorrectPlayer(
      Long gameId, Long playerId, String handSign, int playerNumber) {
    return gameRepository
        .findById(gameId)
        .map(entity -> validateAndUpdatePlayerMoveAndSave(entity, playerId, handSign, playerNumber))
        .orElse(false);
  }

  private boolean validateAndUpdatePlayerMoveAndSave(
      GameEntity gameEntity, Long playerId, String handSign, int playerNumber) {
    PlayerMoveEntity playerMoveEntity = gameEntity.getPlayerMove(playerNumber);

    if (!isPlayerCorrect(playerMoveEntity, playerId)) {
      return false;
    }

    updatePlayerMoveAndSave(gameEntity, playerMoveEntity, handSign, playerNumber);

    return true;
  }

  private void updatePlayerMoveAndSave(
      GameEntity gameEntity, PlayerMoveEntity playerMoveEntity, String handSign, int playerNumber) {
    HandSign handSignEnum = HandSign.valueOf(handSign);

    playerMoveEntity.setHandSign(handSignEnum);
    gameEntity.setPlayerMove(playerMoveEntity, playerNumber);

    gameRepository.save(gameEntity);
  }

  private boolean isPlayerCorrect(PlayerMoveEntity playerMoveEntity, Long playerId) {
    return comparePlayerMoveEntity(playerMoveEntity, playerId)
        && isNotHandSignAlreadySet(playerMoveEntity);
  }

  private boolean isNotHandSignAlreadySet(PlayerMoveEntity playerMoveEntity) {
    return playerMoveEntity.getHandSign() == null;
  }

  private boolean comparePlayerMoveEntity(PlayerMoveEntity playerMoveEntity, Long playerId) {
    return playerMoveEntity.getPlayerId().equals(playerId);
  }

  public Optional<String> getPlayerHandSign(Long gameId, int playerNumber) {
    return gameRepository.findById(gameId)
        .map(gameEntity -> gameEntity.getPlayerMove(playerNumber))
        .map(PlayerMoveEntity::getHandSign)
        .map(Enum::toString);
  }

  public Optional<Long> getPlayerId(Long gameId, int playerNumber) {
    return gameRepository.findById(gameId)
        .map(gameEntity -> gameEntity.getPlayerMove(playerNumber))
        .map(PlayerMoveEntity::getPlayerId);
  }

  public Optional<Integer> getStake(Long gameId) {
    return gameRepository.findById(gameId)
        .map(GameEntity::getStake);
  }
}
