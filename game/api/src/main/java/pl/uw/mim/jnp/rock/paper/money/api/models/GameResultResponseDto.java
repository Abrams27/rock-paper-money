package pl.uw.mim.jnp.rock.paper.money.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameResultResponseDto {

  private String opponentUsername;
  private GameResult gameResult;
}
