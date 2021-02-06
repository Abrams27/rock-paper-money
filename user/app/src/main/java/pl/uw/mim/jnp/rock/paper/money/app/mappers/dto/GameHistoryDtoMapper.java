package pl.uw.mim.jnp.rock.paper.money.app.mappers.dto;

import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.GameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.GameResult;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameHistoryEntity;

@UtilityClass
public class GameHistoryDtoMapper {

  public GameHistoryDto map(GameHistoryEntity gameHistoryEntity) {
    return GameHistoryDto.builder()
        .opponentsUsername(gameHistoryEntity.getOpponentsUsername())
        .gameResult(GameResultMapper.map(gameHistoryEntity.getGameResult()))
        .stake(gameHistoryEntity.getStake())
        .build();
  }

  public List<GameHistoryDto> map(List<GameHistoryEntity> gameHistoryEntityList) {
    return gameHistoryEntityList.stream()
        .map(GameHistoryDtoMapper::map)
        .collect(Collectors.toList());
  }

}
