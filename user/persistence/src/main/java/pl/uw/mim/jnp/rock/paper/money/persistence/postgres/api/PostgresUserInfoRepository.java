package pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api;

import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.user.UserInfoEntity;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.repo.UserInfoRepository;

@Component
@AllArgsConstructor
public class PostgresUserInfoRepository {

  private final UserInfoRepository userInfoRepository;

  public UserInfoEntity addUser(String username, String password, Integer balance) {
    UserInfoEntity entity = createUserInfoEntity(username, password, balance);

    return userInfoRepository.save(entity);
  }

  private UserInfoEntity createUserInfoEntity(String username, String password, Integer balance) {
    return UserInfoEntity.builder()
        .username(username)
        .password(password)
        .balance(balance)
        .build();
  }

  public Optional<UserInfoEntity> getUserInfo(String username) {
    return userInfoRepository.findByUsername(username);
  }
}
