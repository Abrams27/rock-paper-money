package pl.uw.mim.jnp.rock.paper.money.app.services.models;

import java.util.Map;
import pl.uw.mim.jnp.rock.paper.money.api.models.HandSign;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.GameEntity;

public enum GameStatus {
  IN_PROGRESS,
  PLAYER_1_WON,
  PLAYER_2_WON,
  DRAW;

  private static final Map<Integer, GameStatus> COMPARISON_RESULT_TO_GAME_STATUS = Map.of(
      -1, PLAYER_1_WON,
      0, DRAW,
      1, PLAYER_2_WON
  );

  public static GameStatus fromComparisonPlayer1WithPlayer2Result(int comparisonResult) {
    return COMPARISON_RESULT_TO_GAME_STATUS.get(comparisonResult);
  }

  public static GameStatus from(GameEntity gameEntity) {
    if (gameEntity.getPlayer1Move().getHandSign() == null ||
        gameEntity.getPlayer2Move().getHandSign() == null) {
      return GameStatus.IN_PROGRESS;
    }

    String handSignPlayer1 = gameEntity.getPlayer1Move().getHandSign().toString();
    String handSignPlayer2 = gameEntity.getPlayer2Move().getHandSign().toString();

    return GameStatus.from(handSignPlayer1, handSignPlayer2);
  }

  public static GameStatus from(String player1Sign, String player2Sign) {
    HandSign player1HandSign = HandSign.valueOf(player1Sign);
    HandSign player2HandSign = HandSign.valueOf(player2Sign);
    int comparisonResult = player1HandSign.compare(player2HandSign);

    return GameStatus.fromComparisonPlayer1WithPlayer2Result(comparisonResult);
  }


}
