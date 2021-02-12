package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.app.config.AuthProperties;
import pl.uw.mim.jnp.rock.paper.money.app.services.utils.BCryptUtils;
import pl.uw.mim.jnp.rock.paper.money.app.services.utils.JWTGenerator;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.api.PostgresUserInfoRepository;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class AuthService {

  private final PostgresUserInfoRepository postgresUserInfoRepository;
  private final AuthProperties authProperties;

  public Mono<String> loginUserAndCreateToken(String username, String password) {
    return Mono.justOrEmpty(postgresUserInfoRepository.getUserInfo(username))
        .filter(entity-> BCryptUtils.doesPasswordMatch(password, entity.getPassword()))
        .map(entity -> generateJWTToken(entity.getUsername()));
  }

  private String generateJWTToken(String username) {
    return JWTGenerator.generateToken(username, authProperties.getTimeout(), authProperties.getSecret());
  }
}
