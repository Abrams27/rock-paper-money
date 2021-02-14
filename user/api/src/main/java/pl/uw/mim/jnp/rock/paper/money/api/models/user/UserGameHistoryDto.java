package pl.uw.mim.jnp.rock.paper.money.api.models.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGameHistoryDto {

  private String opponentsUsername;

  private UserGameResult userGameResult;

  private Integer stake;
}
