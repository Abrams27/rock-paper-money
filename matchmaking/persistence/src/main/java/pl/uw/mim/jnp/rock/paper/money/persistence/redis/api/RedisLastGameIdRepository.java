package pl.uw.mim.jnp.rock.paper.money.persistence.redis.api;

import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.LastGameIdEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.entries.creators.LastGameIdEntityCreator;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.repo.LastGameIdRepository;

@Component
@AllArgsConstructor
public class RedisLastGameIdRepository {

  private final LastGameIdRepository lastGameIdRepository;

  public Long getNextGameId() {
    LastGameIdEntity gameIdEntity = StreamSupport
        .stream(lastGameIdRepository.findAll().spliterator(), false)
        .findAny()
        .orElse(
            LastGameIdEntityCreator.from(0L));

    gameIdEntity.setGameId(gameIdEntity.getGameId() + 1);
    return lastGameIdRepository.save(gameIdEntity).getGameId();
  }
}
