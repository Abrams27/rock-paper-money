package pl.uw.mim.jnp.rock.paper.money.models.auth;

import lombok.Data;

@Data
public class UserLoginRequestDto {

  private String username;
  private String password;
}
