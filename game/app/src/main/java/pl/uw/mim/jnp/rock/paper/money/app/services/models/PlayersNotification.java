package pl.uw.mim.jnp.rock.paper.money.app.services.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PlayersNotification {

  private Long gameId;
  private String player1;
  private String player2;
  private GameStatus gameStatus;
  private Integer stake;
}
