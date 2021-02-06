package pl.uw.mim.jnp.rock.paper.money.api.models.history;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameHistoryDto {

  private String username1;
  private String username2;

  private GameResultDto gameResult;

  private Integer stake;
}
