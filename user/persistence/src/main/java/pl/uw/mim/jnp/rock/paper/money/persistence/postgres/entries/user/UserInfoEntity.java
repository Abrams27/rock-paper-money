package pl.uw.mim.jnp.rock.paper.money.persistence.postgres.entries.user;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table
public class UserInfoEntity {

  @Id
  private String username;

  private Integer balance;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private List<GameHistoryEntity> gameHistory;
}
