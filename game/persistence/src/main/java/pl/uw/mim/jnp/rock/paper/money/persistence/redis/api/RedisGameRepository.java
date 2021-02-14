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

  public boolean saveGameEntrance(Long gameId, String player1Username, String player2Username, Integer stake) {
    boolean isGameAlreadyRegistered =  gameRepository.findById(gameId).isPresent();

    if (isGameAlreadyRegistered) {
      return false;
    }

    GameEntity gameEntity = GameEntityCreator.from(gameId, player1Username, player2Username, stake);
    gameRepository.save(gameEntity);

    return true;
  }

  public boolean setPlayerMoveIfItIsCorrectPlayer(
      Long gameId, String playerUsername, String handSign, int playerNumber) {
    return gameRepository
        .findById(gameId)
        .map(entity -> validateAndUpdatePlayerMoveAndSave(entity, playerUsername, handSign, playerNumber))
        .orElse(false);
  }

  private boolean validateAndUpdatePlayerMoveAndSave(
      GameEntity gameEntity, String playerUsername, String handSign, int playerNumber) {
    PlayerMoveEntity playerMoveEntity = gameEntity.getPlayerMove(playerNumber);

    if (!isPlayerCorrect(playerMoveEntity, playerUsername)) {
      return false;
    }

    updatePlayerMoveAndSave(gameEntity, playerMoveEntity, handSign, playerNumber);

    return true;
  }

  private void updatePlayerMoveAndSave(
      GameEntity gameEntity, PlayerMoveEntity playerMoveEntity, String handSign, int playerNumber) {
    HandSign handSignEnum = HandSign.valueOf(handSign);

    playerMoveEntity.setHandSign(handSignEnum);
    gameEntity.setHasStarted(true);
    gameEntity.setPlayerMove(playerMoveEntity, playerNumber);

    gameRepository.save(gameEntity);
  }

  private boolean isPlayerCorrect(PlayerMoveEntity playerMoveEntity, String playerUsername) {
    return comparePlayerMoveEntity(playerMoveEntity, playerUsername)
        && isNotHandSignAlreadySet(playerMoveEntity);
  }

  private boolean isNotHandSignAlreadySet(PlayerMoveEntity playerMoveEntity) {
    return playerMoveEntity.getHandSign() == null;
  }

  private boolean comparePlayerMoveEntity(PlayerMoveEntity playerMoveEntity, String playerUsername) {
    return playerMoveEntity.getPlayerUsername().equals(playerUsername);
  }

  public Optional<String> getPlayerHandSign(Long gameId, int playerNumber) {
    return gameRepository.findById(gameId)
        .map(gameEntity -> gameEntity.getPlayerMove(playerNumber))
        .map(PlayerMoveEntity::getHandSign)
        .map(Enum::toString);
  }

  public Optional<String> getPlayerUsername(Long gameId, int playerNumber) {
    return gameRepository.findById(gameId)
        .map(gameEntity -> gameEntity.getPlayerMove(playerNumber))
        .map(PlayerMoveEntity::getPlayerUsername);
  }

  public Optional<Integer> getStake(Long gameId) {
    return gameRepository.findById(gameId)
        .map(GameEntity::getStake);
  }

  public Optional<GameEntity> getNotStartedGameForUsername(String username) {
    return gameRepository.findAll().stream()
        .filter(entity -> !entity.getHasStarted())
        .filter(entity ->
            compareUsernames(entity.getPlayer1Move(), username) ||
                compareUsernames(entity.getPlayer2Move(), username))
        .findFirst();
  }

  private boolean compareUsernames(PlayerMoveEntity playerMoveEntity, String username) {
    return playerMoveEntity.getPlayerUsername().equals(username);
  }

  public Optional<GameEntity> getGameWithId(Long gameId) {
    return gameRepository.findById(gameId);
  }
}
