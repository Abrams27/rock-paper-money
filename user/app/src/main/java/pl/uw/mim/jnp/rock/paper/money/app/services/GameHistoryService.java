package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.models.user.history.GameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.app.mappers.entity.GameResultMapper;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api.PostgresGameHistoryRepository;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameHistoryEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameResult;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class GameHistoryService {

  private final PostgresGameHistoryRepository postGameHistoryForUser;

  public Mono<Void> postGameHistory(GameHistoryDto gameHistoryDto) {
    Mono<GameHistoryEntity> postGameHistoryUser1 = Mono.just(gameHistoryDto)
        .map(this::postGameHistoryForUser1);

    Mono<GameHistoryEntity> postGameHistoryUser2 = Mono.just(gameHistoryDto)
        .map(this::postGameHistoryForUser2);

    return Mono.zip(postGameHistoryUser1, postGameHistoryUser2)
        .then();
  }

  private GameHistoryEntity postGameHistoryForUser1(GameHistoryDto gameHistoryDto) {
    String username = gameHistoryDto.getUsername1();
    String opponentUsername = gameHistoryDto.getUsername2();
    GameResult gameResult = GameResultMapper.map(gameHistoryDto.getGameResult(), 1);

    return postGameHistoryForUser.postGameHistoryForUser(username, opponentUsername, gameResult, gameHistoryDto.getStake());
  }

  private GameHistoryEntity postGameHistoryForUser2(GameHistoryDto gameHistoryDto) {
    String username = gameHistoryDto.getUsername2();
    String opponentUsername = gameHistoryDto.getUsername1();
    GameResult gameResult = GameResultMapper.map(gameHistoryDto.getGameResult(), 2);

    return postGameHistoryForUser.postGameHistoryForUser(username, opponentUsername, gameResult, gameHistoryDto.getStake());
  }
}
