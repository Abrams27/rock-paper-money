package pl.uw.mim.jnp.rock.paper.money.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserGameResponseDto {

  private Long gameId;

  private String opponentUsername;

  private Integer stake;
}
