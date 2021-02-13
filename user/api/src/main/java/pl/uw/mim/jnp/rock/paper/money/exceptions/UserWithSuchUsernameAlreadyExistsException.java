package pl.uw.mim.jnp.rock.paper.money.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class UserWithSuchUsernameAlreadyExistsException extends RuntimeException {

  private static final String MESSAGE = "User with such username already exists";

  public UserWithSuchUsernameAlreadyExistsException() {
    super(MESSAGE);
  }
}
