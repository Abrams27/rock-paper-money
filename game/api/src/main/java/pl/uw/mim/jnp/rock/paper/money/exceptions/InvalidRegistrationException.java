package pl.uw.mim.jnp.rock.paper.money.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidRegistrationException extends RuntimeException {

  private static final String MESSAGE = "Game is already Registered";

  public InvalidRegistrationException() {
    super(MESSAGE);
  }
}
