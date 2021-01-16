package pl.uw.mim.jnp.rock.paper.money.app.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.uw.mim.jnp.rock.paper.money.app.services.models.GameStatus;

@Service
@AllArgsConstructor
public class NotificationService {

  public void notifyPlayersAboutResult(Long gameId, Long player1, Long player2, GameStatus gameStatus) {
    System.out.println("[GAME RESULT] "
        + "gameId: " + gameId
        + ", result: " + gameStatus
        + ", player1Id: " + player1
        + ", player2Id: " + player2);
  }
}
