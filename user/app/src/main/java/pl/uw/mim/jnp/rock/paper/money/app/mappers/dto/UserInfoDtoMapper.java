package pl.uw.mim.jnp.rock.paper.money.app.mappers.dto;

import lombok.experimental.UtilityClass;
import pl.uw.mim.jnp.rock.paper.money.models.user.UserInfoDto;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.user.UserInfoEntity;

@UtilityClass
public class UserInfoDtoMapper {

  public UserInfoDto map(UserInfoEntity userInfoEntity) {
    return UserInfoDto.builder()
        .username(userInfoEntity.getUsername())
        .balance(userInfoEntity.getBalance())
        .gameHistory(UserGameHistoryDtoMapper.map(userInfoEntity.getGameHistory()))
        .build();
  }
}
