package pl.uw.mim.jnp.rock.paper.money.app.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.uw.mim.jnp.rock.paper.money.api.endpoints.UserApi;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.history.GameHistoryDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.RegisterUserDataDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.user.UserInfoDto;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.user.GetUserInfo;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.user.PostHistory;
import pl.uw.mim.jnp.rock.paper.money.app.usecases.user.RegisterUser;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {

  private final RegisterUser registerUser;
  private final GetUserInfo getUserInfo;
  private final PostHistory postHistory;

  @Override
  public Mono<Void> registerUser(RegisterUserDataDto registerUserData) {
    return registerUser.execute(registerUserData);
  }

  @Override
  public Mono<UserInfoDto> getUserInfo(String username) {
    return getUserInfo.execute(username);
  }

  @Override
  public Mono<Void> postHistory(GameHistoryDto gameHistory) {
    return postHistory.execute(gameHistory);
  }
}
