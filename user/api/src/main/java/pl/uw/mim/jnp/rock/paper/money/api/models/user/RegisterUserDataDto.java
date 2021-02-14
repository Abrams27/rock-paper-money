package pl.uw.mim.jnp.rock.paper.money.api.models.user;

import lombok.Data;

@Data
public class RegisterUserDataDto {

  private String username;
  private String password;
}
