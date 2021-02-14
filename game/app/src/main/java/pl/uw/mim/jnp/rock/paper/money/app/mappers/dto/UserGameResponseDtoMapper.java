package pl.uw.mim.jnp.rock.paper.money.app.mappers.dto;

import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.api.models.UserGameResponseDto;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.GameEntity;

@UtilityClass
public class UserGameResponseDtoMapper {

  public UserGameResponseDto map(GameEntity gameEntity, String username) {
    return UserGameResponseDto.builder()
        .opponentUsername(getOpponentUsername(gameEntity, username))
        .gameId(gameEntity.getId())
        .stake(gameEntity.getStake())
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
}
