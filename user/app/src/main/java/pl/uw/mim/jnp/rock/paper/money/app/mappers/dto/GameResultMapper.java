package pl.uw.mim.jnp.rock.paper.money.app.mappers.dto;

import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.GameResult;

@UtilityClass
public class GameResultMapper {

  public GameResult map(pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameResult gameResult) {
    return GameResult.valueOf(gameResult.name());
  }

}
