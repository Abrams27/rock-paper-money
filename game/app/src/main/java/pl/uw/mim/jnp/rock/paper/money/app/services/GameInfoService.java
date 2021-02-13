package pl.uw.mim.jnp.rock.paper.money.app.services;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.api.models.UserGameResponseDto;
import pl.uw.mim.jnp.rock.paper.money.app.mappers.dto.UserGameResponseDtoMapper;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.api.RedisGameRepository;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class GameInfoService {

  private final RedisGameRepository redisGameRepository;

  public Mono<UserGameResponseDto> getRecentGameForUsername(String username) {
    return Mono.just(username)
        .map(redisGameRepository::getNotStartedGameForUsername)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(entity -> UserGameResponseDtoMapper.map(entity, username));
  }
}
