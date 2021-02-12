package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.UserInfoDto;
import pl.uw.mim.jnp.rock.paper.money.app.config.UserProperties;
import pl.uw.mim.jnp.rock.paper.money.app.mappers.dto.UserInfoDtoMapper;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api.PostgresUserInfoRepository;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class UserInfoService {

  private final PostgresUserInfoRepository postgresUserInfoRepository;
  private final UserProperties userProperties;

  public Mono<Void> addUser(String username, String password) {
    Integer startingBalance = userProperties.getBalance();

    return Mono.just(postgresUserInfoRepository.addUser(username, password, startingBalance))
        .then();
  }

  public Mono<UserInfoDto> getUserInfo(String username) {
    return Mono.justOrEmpty(postgresUserInfoRepository.getUserInfo(username))
        .map(UserInfoDtoMapper::map);
  }
}
