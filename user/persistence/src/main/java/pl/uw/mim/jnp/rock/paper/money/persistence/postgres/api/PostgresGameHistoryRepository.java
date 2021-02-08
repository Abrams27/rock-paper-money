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

  public GameHistoryEntity postGameHistoryForUser(String username, String opponentsUsername, GameResult gameResult, Integer stake) {
//    GameHistoryEntity gameHistoryEntity = createGameHistoryEntity(opponentsUsername, gameResult, stake);
//    GameHistoryEntity savedGameHistoryEntity = gameHistoryRepository.save(gameHistoryEntity);

    return userInfoRepository.findByUsername(username)
        .map(userInfoEntity -> createGameHistoryEntity(userInfoEntity, opponentsUsername, gameResult, stake))
        .map(gameHistoryRepository::save)
        .orElseThrow();
  }

  private GameHistoryEntity createGameHistoryEntity(UserInfoEntity userInfoEntity, String opponentsUsername, GameResult gameResult, Integer stake) {
    return GameHistoryEntity.builder()
        .userInfoEntity(userInfoEntity)
        .gameResult(gameResult)
        .opponentUsername(opponentsUsername)
        .stake(stake)
        .build();
  }

  private UserInfoEntity addGameHistory(UserInfoEntity userInfoEntity, GameHistoryEntity gameHistoryEntity) {
    System.out.println("->>>>>>" + userInfoEntity);
    System.out.println("->>>>>>" + gameHistoryEntity);
    System.out.println("?-> " + gameHistoryRepository.findAll());
    userInfoEntity.addGameHistory(gameHistoryEntity);

    return userInfoEntity;
  }
}
