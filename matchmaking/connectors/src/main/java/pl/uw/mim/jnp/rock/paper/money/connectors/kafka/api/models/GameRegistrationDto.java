package pl.uw.mim.jnp.rock.paper.money.connectors.kafka.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameRegistrationDto {
  private Long gameId;

  private String player1Username;
  private String player2Username;

  private Integer stake;
}
