package pl.uw.mim.jnp.rock.paper.money.connectors.user.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameHistory {

  private String username1;
  private String username2;

  private GameResult gameResult;

  private Integer stake;
}
