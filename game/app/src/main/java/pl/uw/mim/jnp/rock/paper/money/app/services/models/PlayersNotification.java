package pl.uw.mim.jnp.rock.paper.money.app.services.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayersNotification {

  private Long gameId;
  private Long player1;
  private Long player2;
  private GameStatus gameStatus;
  private Integer stake;
}
