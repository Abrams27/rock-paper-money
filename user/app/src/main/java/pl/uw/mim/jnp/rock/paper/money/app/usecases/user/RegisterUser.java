package pl.uw.mim.jnp.rock.paper.money.app.usecases.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.RegisterUserDataDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.UserInfoService;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RegisterUser {

  private final UserInfoService userInfoService;

  public Mono<Void> execute(RegisterUserDataDto registerUserData) {
    return userInfoService
        .addUser(registerUserData.getUsername(), registerUserData.getPassword())
        .then();
  }
}
