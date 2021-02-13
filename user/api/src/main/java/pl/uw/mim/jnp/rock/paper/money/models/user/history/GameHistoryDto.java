package pl.uw.mim.jnp.rock.paper.money.models.user.history;

import lombok.Data;

@Data
public class GameHistoryDto {

  private String username1;
  private String username2;

  private GameResultDto gameResult;

  private Integer stake;
}
