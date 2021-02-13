package pl.uw.mim.jnp.rock.paper.money.app.mappers.entity;

import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.models.user.history.GameResultDto;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameResult;

@UtilityClass
public class GameResultMapper {

  public GameResult map(GameResultDto gameResult, int playerNumer) {
    if (gameResult.equals(GameResultDto.DRAW)) {
      return GameResult.DRAW;
    }

    if (gameResult.equals(GameResultDto.PLAYER_1_WON) && playerNumer == 1) {
      return GameResult.WIN;
    }

    if (gameResult.equals(GameResultDto.PLAYER_2_WON) && playerNumer == 2) {
      return GameResult.WIN;
    }

    return GameResult.LOSE;
  }
}
