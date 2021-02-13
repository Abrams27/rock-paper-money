package pl.uw.mim.jnp.rock.paper.money.app.services;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.models.HandSign;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.api.RedisGameRepository;

@Service
@AllArgsConstructor
public class GameService {

  private final RedisGameRepository redisGameRepository;

  public boolean registerGame(Long gameId, String player1Username, String player2Username, Integer stake) {
    return redisGameRepository.saveGameEntrance(gameId, player1Username, player2Username, stake);
  }

  public boolean enterGame(Long gameId, String playerUsername, HandSign handSign) {
    boolean player1EntranceResult =
        redisGameRepository.setPlayerMoveIfItIsCorrectPlayer(gameId, playerUsername, handSign.toString(), 1);
    boolean player2EntranceResult =
        redisGameRepository.setPlayerMoveIfItIsCorrectPlayer(gameId, playerUsername, handSign.toString(), 2);

    return player1EntranceResult || player2EntranceResult;
  }

  public String getPlayer1Username(Long gameId) {
    return redisGameRepository
        .getPlayerUsername(gameId, 1)
        .orElseThrow();
  }

  public String getPlayer2Username(Long gameId) {
    return redisGameRepository
        .getPlayerUsername(gameId, 2)
        .orElseThrow();
  }

  public Integer getStake(Long gameId) {
    return redisGameRepository
        .getStake(gameId)
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
