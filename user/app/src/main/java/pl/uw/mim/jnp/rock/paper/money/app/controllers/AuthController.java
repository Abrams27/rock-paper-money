package pl.uw.mim.jnp.rock.paper.money.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.uw.mim.jnp.rock.paper.money.api.endpoints.AuthApi;
import pl.uw.mim.jnp.rock.paper.money.api.models.auth.UserLoginRequestDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.auth.UserLoginResponseDto;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.auth.Authenticate;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.auth.Login;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.auth.Logout;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class AuthController implements AuthApi {

  private final Login login;
  private final Authenticate authenticate;
  private final Logout logout;

  @Override
  public Mono<UserLoginResponseDto> login(UserLoginRequestDto userLoginRequest) {
    return login.execute(userLoginRequest);
  }

  @Override
  public Mono<Void> authenticate(String authorizationHeader) {
    return authenticate.execute(authorizationHeader);
  }

  @Override
  public Mono<Void> logout(String authorizationHeader) {
    return logout.execute(authorizationHeader);
  }
}
