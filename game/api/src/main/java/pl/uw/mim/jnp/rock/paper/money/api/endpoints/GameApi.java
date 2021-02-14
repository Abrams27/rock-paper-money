package pl.uw.mim.jnp.rock.paper.money.api.endpoints;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameEntranceDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameProgress;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameRegistrationDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.GameResultResponseDto;
import pl.uw.mim.jnp.rock.paper.money.api.models.UserGameResponseDto;
import reactor.core.publisher.Mono;

@RequestMapping("/api/game-service/game")
public interface GameApi {

  @PostMapping("/register")
  Mono<Void> registerGame(
      @RequestBody GameRegistrationDto gameRegistration);

  @PostMapping("/enter")
  Mono<Void> enterGame(
    @RequestBody GameEntranceDto gameEntrance);

  @GetMapping("/user/new")
  Mono<UserGameResponseDto> newGame(
      @RequestParam("username") String username);

  @GetMapping("/{gameId}/info")
  Mono<GameProgress> gameInfo(
      @PathVariable("gameId") Long gameId);

  @GetMapping("/{gameId}/result")
  Mono<GameResultResponseDto> gameResult(
      @PathVariable("gameId") Long gameId,
      @RequestParam("username") String username);
}
