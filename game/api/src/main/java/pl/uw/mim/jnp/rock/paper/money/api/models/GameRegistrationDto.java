package pl.uw.mim.jnp.rock.paper.money.api.models;

import lombok.Data;

@Data
public class GameRegistrationDto {

  private Long gameId;

  private Long player1Id;
  private Long player2Id;

  private Integer stake;
}
