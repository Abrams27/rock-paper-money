package pl.uw.mim.jnp.rock.paper.money.app.usecases.auth;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.uw.mim.jnp.rock.paper.money.api.exceptions.InvalidCredentialsException;
import pl.uw.mim.jnp.rock.paper.money.api.models.auth.UserLoginRequestDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.auth.UserLoginResponseDto;
import pl.uw.mim.jnp.rock.paper.money.app.services.AuthService;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class Login {

  private final AuthService authService;

  public Mono<UserLoginResponseDto> execute(UserLoginRequestDto userLoginRequest) {
    return Mono.just(userLoginRequest)
        .flatMap(loginRequest -> authService.loginUserAndCreateToken(loginRequest.getUsername(), loginRequest.getPassword()))
        .map(this::createUserLoginResponseDto)
        .switchIfEmpty(Mono.error(new InvalidCredentialsException()));
  }

  private UserLoginResponseDto createUserLoginResponseDto(String token) {
    return UserLoginResponseDto.builder()
        .token(token)
        .build();
  }
}
