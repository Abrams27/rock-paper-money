package pl.uw.mim.jnp.rock.paper.money.app.services.models;

import java.util.Map;

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
}
