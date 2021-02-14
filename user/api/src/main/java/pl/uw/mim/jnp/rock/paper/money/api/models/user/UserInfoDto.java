package pl.uw.mim.jnp.rock.paper.money.api.models.user;

import java.util.List;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserInfoDto {

  private String username;

  private Integer balance;

  private List<UserGameHistoryDto> gameHistory;
}
