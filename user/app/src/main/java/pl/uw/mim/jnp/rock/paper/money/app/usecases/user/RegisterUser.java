package pl.uw.mim.jnp.rock.paper.money.app.usecases.user;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.exceptions.UserWithSuchUsernameAlreadyExistsException;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.RegisterUserDataDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.UserInfoService;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RegisterUser {

  private final UserInfoService userInfoService;

  public Mono<Void> execute(RegisterUserDataDto registerUserData) {

    return Mono.just(registerUserData)
        .doOnNext(userData -> throwIfUsernameIsAlreadyUsed(userData.getUsername()))
        .flatMap(userData-> userInfoService.addUser(userData.getUsername(), userData.getPassword()))
        .then();
  }

  private void throwIfUsernameIsAlreadyUsed(String username) {
    boolean isUsernameAlreadyTaken = userInfoService.getUserInfo(username).blockOptional().isPresent();

    if (isUsernameAlreadyTaken) {
      throw new UserWithSuchUsernameAlreadyExistsException();
    }
  }
}
