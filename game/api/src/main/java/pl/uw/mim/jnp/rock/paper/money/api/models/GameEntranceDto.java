package pl.uw.mim.jnp.rock.paper.money.api.models;

import lombok.Data;

@Data
public class GameEntranceDto {

  private Long gameId;

  private Long playerId;

  private HandSign handSign;
}
