package pl.uw.mim.jnp.rock.paper.money.app.services;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameProgress;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameResultResponseDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.UserGameResponseDto;
import pl.uw.mim.jnp.rock.paper.money.app.mappers.dto.GameResultResponseDtoMapper;
import pl.uw.mim.jnp.rock.paper.money.app.mappers.dto.UserGameResponseDtoMapper;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.api.RedisGameRepository;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.GameEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.PlayerMoveEntity;
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

  public Mono<GameProgress> getGameProgress(Long gameId) {
    return Mono.just(gameId)
        .map(redisGameRepository::getGameWithId)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .map(this::mapGameProgress);
  }

  private GameProgress mapGameProgress(GameEntity gameEntity) {
    if (hasPlayerMoved(gameEntity.getPlayer1Move()) && hasPlayerMoved(gameEntity.getPlayer2Move())) {
      return GameProgress.ENDED;
    }

    return GameProgress.IN_PROGRESS;
  }

  private Boolean hasPlayerMoved(PlayerMoveEntity playerMoveEntity) {
    return playerMoveEntity.getHandSign() != null;
  }

  public Mono<GameResultResponseDto> getGame(Long gameId, String username) {
    return Mono.just(gameId)
        .map(redisGameRepository::getGameWithId)
        .filter(Optional::isPresent)
        .map(Optional::get)
        .filter(this::didGameEnd)
        .map(entity -> GameResultResponseDtoMapper.map(entity, username));
  }

  private boolean didGameEnd(GameEntity gameEntity) {
    return !GameStatus.from(gameEntity).equals(GameStatus.IN_PROGRESS);
  }
}
