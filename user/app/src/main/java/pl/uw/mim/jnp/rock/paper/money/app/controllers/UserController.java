package pl.uw.mim.jnp.rock.paper.money.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.uw.mim.jnp.rock.paper.money.api.endpoints.UserApi;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.GameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.UserInfoDto;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.user.GetUserInfo;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.user.PostHistory;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

  private final GetUserInfo getUserInfo;
  private final PostHistory postHistory;

  @Override
  public Mono<UserInfoDto> getUserInfo(String username) {
    return getUserInfo.execute(username);
  }

  @Override
  public Mono<Void> postHistory(GameHistoryDto gameHistory) {
    return postHistory.execute(gameHistory);
  }
}
