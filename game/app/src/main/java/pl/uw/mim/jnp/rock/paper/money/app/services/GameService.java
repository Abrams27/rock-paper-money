package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.api.RedisGameRepository;

@Service
@AllArgsConstructor
public class GameService {

  private final RedisGameRepository redisGameRepository;

  public void registerGame(Long gameId, Long player1Id, Long player2Id, Integer stake) {
    redisGameRepository.saveGameEntrance(gameId, player1Id, player2Id, stake);
  }


}
