package pl.uw.mim.jnp.rock.paper.money.api.endpoints;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.history.GameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.RegisterUserDataDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.UserInfoDto;
import reactor.core.publisher.Mono;

@CrossOrigin
@RequestMapping("/api/user-service/user")
public interface UserApi {

  @PostMapping("/register")
  Mono<Void> registerUser(
      @RequestBody RegisterUserDataDto registerUserdata);

  @GetMapping("/{username}/info")
  Mono<UserInfoDto> getUserInfo(
      @PathVariable("username") String username);

  @PostMapping("/history")
  Mono<Void> postHistory(
      @RequestBody GameHistoryDto gameHistory);
}
