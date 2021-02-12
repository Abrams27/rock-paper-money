package pl.uw.mim.jnp.rock.paper.money.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.uw.mim.jnp.rock.paper.money.api.endpoints.AuthApi;
import pl.uw.mim.jnp.rock.paper.money.api.models.auth.UserLoginRequestDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.auth.UserLoginResponseDto;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.auth.Login;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class AuthController implements AuthApi {

  private final Login login;

  @Override
  public Mono<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequest) {
    return login.execute(userLoginRequest);
  }
}
