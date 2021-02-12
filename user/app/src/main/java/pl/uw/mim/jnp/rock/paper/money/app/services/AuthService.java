package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.app.config.AuthProperties;
import pl.uw.mim.jnp.rock.paper.money.app.services.utils.BCryptUtils;
import pl.uw.mim.jnp.rock.paper.money.app.services.utils.JWTGenerator;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api.PostgresUserInfoRepository;
import pl.uw.mim.jnp.rock.paper.money.persistence.redis.api.RedisLoggedUserRepository;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthService {

  private final PostgresUserInfoRepository postgresUserInfoRepository;
  private final RedisLoggedUserRepository redisLoggedUserRepository;
  private final AuthProperties authProperties;

  public Mono<String> loginUserAndCreateToken(String username, String password) {
    return Mono.justOrEmpty(postgresUserInfoRepository.getUserInfo(username))
        .filter(entity-> BCryptUtils.doesPasswordMatch(password, entity.getPassword()))
        .map(entity -> generateJWTTokenAndSave(entity.getUsername()));
  }

  private String generateJWTTokenAndSave(String username) {
    String token = JWTGenerator.generateToken(username, authProperties.getSecret());

    redisLoggedUserRepository.saveUsername(username);

    return token;
  }
}
