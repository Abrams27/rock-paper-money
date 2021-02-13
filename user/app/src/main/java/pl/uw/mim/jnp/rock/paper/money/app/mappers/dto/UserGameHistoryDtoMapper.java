package pl.uw.mim.jnp.rock.paper.money.app.mappers.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.models.user.UserGameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameHistoryEntity;

@UtilityClass
public class UserGameHistoryDtoMapper {

  public UserGameHistoryDto map(GameHistoryEntity gameHistoryEntity) {
    return UserGameHistoryDto.builder()
        .opponentsUsername(gameHistoryEntity.getOpponentUsername())
        .userGameResult(UserGameResultMapper.map(gameHistoryEntity.getGameResult()))
        .stake(gameHistoryEntity.getStake())
        .build();
  }

  public List<UserGameHistoryDto> map(List<GameHistoryEntity> gameHistoryEntityList) {
    return gameHistoryEntityList.stream()
        .map(UserGameHistoryDtoMapper::map)
        .collect(Collectors.toList());
  }

}
