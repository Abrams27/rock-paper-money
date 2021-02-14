package pl.uw.mim.jnp.rock.paper.money.app.mappers.dto;

import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameResult;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameResultResponseDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.GameEntity;

@UtilityClass
public class GameResultResponseDtoMapper {

  public GameResultResponseDto map(GameEntity gameEntity, String username) {
    return GameResultResponseDto.builder()
        .opponentUsername(getOpponentUsername(gameEntity, username))
        .gameResult(getGameResult(gameEntity, username))
        .build();
  }

  private String getOpponentUsername(GameEntity gameEntity, String username) {
    String player1Username = gameEntity.getPlayer1Move().getPlayerUsername();
    String player2Username = gameEntity.getPlayer2Move().getPlayerUsername();

    if (player1Username.equals(username)) {
      return player2Username;
    }

    return player1Username;
  }

  private GameResult getGameResult(GameEntity gameEntity, String username) {
    GameStatus gameStatus = GameStatus.from(gameEntity);
    int playerNumber = getPlayerNumber(gameEntity, username);

    return getGameResult(gameStatus, playerNumber);
  }

  private int getPlayerNumber(GameEntity gameEntity, String username) {
    if (gameEntity.getPlayer1Move().getPlayerUsername().equals(username)) {
      return 1;
    }

    return 2;
  }

  private GameResult getGameResult(GameStatus gameStatus, int playerNumber) {
    if (playerNumber == 1 && gameStatus.equals(GameStatus.PLAYER_1_WON)) {
      return GameResult.WIN;
    }

    if (playerNumber == 2 && gameStatus.equals(GameStatus.PLAYER_2_WON)) {
      return GameResult.WIN;
    }

    return GameResult.DRAW;
  }
}
