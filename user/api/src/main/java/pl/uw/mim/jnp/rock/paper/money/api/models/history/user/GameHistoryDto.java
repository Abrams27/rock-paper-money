package pl.uw.mim.jnp.rock.paper.money.api.models.history.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameHistoryDto {

  private String opponentsUsername;

  private GameResult gameResult;

  private Integer stake;
}
