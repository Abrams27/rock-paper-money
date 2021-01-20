package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameResultNotification {

  private Long gameId;

  private Long player1;
  private Long player2;

  private Integer stake;
  private GameResult gameResult;
}
