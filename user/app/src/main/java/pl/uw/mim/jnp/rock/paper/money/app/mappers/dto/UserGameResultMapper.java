package pl.uw.mim.jnp.rock.paper.money.app.mappers.dto;

import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.UserGameResult;

@UtilityClass
public class UserGameResultMapper {

  public UserGameResult map(pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameResult gameResult) {
    return UserGameResult.valueOf(gameResult.name());
  }

}
