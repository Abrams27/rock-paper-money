package pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameHistoryEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameResult;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.user.UserInfoEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.repo.GameHistoryRepository;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.repo.UserInfoRepository;

@Component
@AllArgsConstructor
public class PostgresGameHistoryRepository {

  private final GameHistoryRepository gameHistoryRepository;
  private final UserInfoRepository userInfoRepository;

  public GameHistoryEntity postGameHistoryForUser(String username, String opponentsUsername, GameResult gameResult, Integer stake, Integer stakeDiff) {
    return userInfoRepository.findByUsername(username)
        .map(userInfoEntity -> createGameHistoryEntity(userInfoEntity, opponentsUsername, gameResult, stake, stakeDiff))
        .map(gameHistoryRepository::save)
        .orElseThrow();
  }

  private GameHistoryEntity createGameHistoryEntity(UserInfoEntity userInfoEntity, String opponentsUsername, GameResult gameResult, Integer stake, Integer stakeDiff) {
    userInfoEntity.setBalance(userInfoEntity.getBalance() + stakeDiff);

    return GameHistoryEntity.builder()
        .userInfoEntity(userInfoEntity)
        .gameResult(gameResult)
        .opponentUsername(opponentsUsername)
        .stake(stake)
        .build();
  }

}
