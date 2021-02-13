package pl.uw.mim.jnp.rock.paper.money.api.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.uw.mim.jnp.rock.paper.money.api.models.auth.UserLoginRequestDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.auth.UserLoginResponseDto;
import reactor.core.publisher.Mono;

@RequestMapping("/api/user-service/auth")
public interface AuthApi {

  @PostMapping("/login")
  Mono<UserLoginResponseDto> login(
      @RequestBody UserLoginRequestDto userLoginRequest);

  @GetMapping("/authenticate")
  Mono<Void> authenticate(
      @RequestHeader("Authorization") String authorizationHeader);

  @GetMapping("/logout")
  Mono<Void> logout(
      @RequestHeader("Authorization") String authorizationHeader);
}
