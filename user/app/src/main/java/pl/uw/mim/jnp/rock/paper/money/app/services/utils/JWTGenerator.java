package pl.uw.mim.jnp.rock.paper.money.app.services.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JWTGenerator {

  public String generateToken(String username, String secret) {
    return Jwts.builder()
        .setSubject(username)
        .signWith(SignatureAlgorithm.HS512, secret)
        .compact();
  }
}
