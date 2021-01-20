package pl.uw.mim.jnp.rock.paper.money.api.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.uw.mim.jnp.rock.paper.money.api.models.history.user.GameHistoryDto;
import reactor.core.publisher.Flux;

@RequestMapping("/api/user-service/user")
public interface UserApi {

  @GetMapping("/{id}/history")
  Flux<GameHistoryDto> getGamesHistory(
      @PathVariable("id") Long userId);

}
