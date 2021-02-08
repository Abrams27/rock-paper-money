package pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api;

import java.util.List;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameHistoryEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameResult;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.user.UserInfoEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.repo.UserInfoRepository;

@Component
@AllArgsConstructor
public class PostgresUserInfoRepository {

  private final UserInfoRepository userInfoRepository;

  public Optional<UserInfoEntity> getHistoryForUser(String username) {
    userInfoRepository.save(UserInfoEntity.builder()
        .username("meme")
        .balance(2137)
        .gameHistory(List.of())
        .build());

    userInfoRepository.save(UserInfoEntity.builder()
        .username("chuj")
        .balance(213317)
        .gameHistory(List.of())
        .build());

    return userInfoRepository.findByUsername(username);
  }
}
