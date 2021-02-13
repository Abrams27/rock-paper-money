package pl.uw.mim.jnp.rock.paper.money.models;

import lombok.Data;

@Data
public class GameEntranceDto {

  private Long gameId;

  private String playerUsername;

  private HandSign handSign;
}
