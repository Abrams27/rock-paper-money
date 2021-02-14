package pl.uw.mim.jnp.rock.paper.money.api.models;

import java.util.Map;

public enum HandSign {
  ROCK,
  PAPER,
  SCISSORS;

  private static final Map<HandSign, HandSign> WINNING_SIGN = Map.of(
      ROCK, PAPER,
      PAPER, SCISSORS,
      SCISSORS, ROCK
  );

  public int compare(HandSign handSign) {
    if (ordinal() == handSign.ordinal()) {
      return 0;
    }

    if (WINNING_SIGN.get(this).ordinal() == handSign.ordinal()) {
      return 1;
    }

    return -1;
  }

}
