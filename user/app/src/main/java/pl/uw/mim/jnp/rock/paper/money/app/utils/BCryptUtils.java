package pl.uw.mim.jnp.rock.paper.money.app.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@UtilityClass
public class BCryptUtils {

  private final BCryptPasswordEncoder ENCODER = new BCryptPasswordEncoder();

  public String encodePassword(String password) {
    return ENCODER.encode(password);
  }

  public boolean doesPasswordMatch(String rawPassword, String encodedPassword) {
    return ENCODER.matches(rawPassword, encodedPassword);
  }
}
