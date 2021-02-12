package pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.user;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.history.GameHistoryEntity;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Entity
@Table(name = "USER_INFO")
public class UserInfoEntity {

  @Id
  private String username;

  private String password;

  private Integer balance;

  @OneToMany(mappedBy="userInfoEntity")
  private List<GameHistoryEntity> gameHistory;

  public void addGameHistory(GameHistoryEntity gameHistoryEntity) {
    gameHistory.add(gameHistoryEntity);
  }
}
