package pl.uw.mim.jnp.rock.paper.money.api.models.auth;

import lombok.Data;

@Data
public class UserLoginRequestDto {

  private String username;
  private String password;
}
