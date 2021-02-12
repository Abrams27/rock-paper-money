package pl.uw.mim.jnp.rock.paper.money.app.services.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JWTGenerator {

  public String generateToken(String username, Integer timeoutInSeconds, String secret) {
    return Jwts.builder()
        .setSubject(username)
        .setExpiration(getTimeoutDate(timeoutInSeconds))
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }

  private Date getTimeoutDate(Integer timeoutInSeconds) {
    return new Date(System.currentTimeMillis() + timeoutInSeconds * 1000);
  }

}
