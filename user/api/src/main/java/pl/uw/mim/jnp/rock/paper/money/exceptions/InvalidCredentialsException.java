package pl.uw.mim.jnp.rock.paper.money.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidCredentialsException extends RuntimeException {

  private static final String MESSAGE = "Invalid credentials";

  public InvalidCredentialsException() {
    super(MESSAGE);
  }
}
