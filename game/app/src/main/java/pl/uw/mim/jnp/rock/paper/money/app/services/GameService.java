package pl.uw.mim.jnp.rock.paper.money.app.services;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.api.models.HandSign;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.api.RedisGameRepository;

@Service
@AllArgsConstructor
public class GameService {

  private final RedisGameRepository redisGameRepository;

  public void registerGame(Long gameId, Long player1Id, Long player2Id, Integer stake) {
    redisGameRepository.saveGameEntrance(gameId, player1Id, player2Id, stake);
  }

  public boolean enterGame(Long gameId, Long playerId, HandSign handSign) {
    boolean player1EntranceResult =
        redisGameRepository.setPlayerMoveIfItIsCorrectPlayer(gameId, playerId, handSign.toString(), 1);
    boolean player2EntranceResult =
        redisGameRepository.setPlayerMoveIfItIsCorrectPlayer(gameId, playerId, handSign.toString(), 2);

    return player1EntranceResult || player2EntranceResult;
  }

  public Long getPlayer1Id(Long gameId) {
    return redisGameRepository
        .getPlayerId(gameId, 1)
        .orElseThrow();
  }

  public Long getPlayer2Id(Long gameId) {
    return redisGameRepository
        .getPlayerId(gameId, 2)
        .orElseThrow();
  }

  public GameStatus getGameStatus(Long gameId) {
    Optional<String> player1Sign = redisGameRepository.getPlayerHandSign(gameId, 1);
    Optional<String> player2Sign = redisGameRepository.getPlayerHandSign(gameId, 2);

    if (player1Sign.isPresent() && player2Sign.isPresent()) {
      return resolveGameStatusForPlayerSigns(player1Sign.get(), player2Sign.get());
    }

    return GameStatus.IN_PROGRESS;
  }

  private GameStatus resolveGameStatusForPlayerSigns(String player1Sign, String player2Sign) {
    HandSign player1HandSign = HandSign.valueOf(player1Sign);
    HandSign player2HandSign = HandSign.valueOf(player2Sign);
    int comparisonResult = player1HandSign.compare(player2HandSign);

    return GameStatus.fromComparisonPlayer1WithPlayer2Result(comparisonResult);
  }
}
