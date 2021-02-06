package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.UserInfoDto;
import pl.uw.mim.jnp.rock.paper.money.app.mappers.dto.UserInfoDtoMapper;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api.PostgresUserInfoRepository;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserInfoService {

  private final PostgresUserInfoRepository postgresUserInfoRepository;

  public Mono<UserInfoDto> getHistoryForUser(String username) {
    return Mono.justOrEmpty(postgresUserInfoRepository.getHistoryForUserId(username))
        .map(UserInfoDtoMapper::map);
  }
}
