package pl.uw.mim.jnp.rock.paper.money.api.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.UserInfoDto;
import reactor.core.publisher.Mono;

@RequestMapping("/api/user-service/user")
public interface UserApi {

  @GetMapping("/{username}/info")
  Mono<UserInfoDto> getUserInfo(
      @PathVariable("username") String username);
}
