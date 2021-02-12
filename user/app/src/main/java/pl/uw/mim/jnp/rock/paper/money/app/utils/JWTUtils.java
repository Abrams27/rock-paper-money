package pl.uw.mim.jnp.rock.paper.money.app.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JWTUtils {

  private final SignatureAlgorithm ALGORITHM = SignatureAlgorithm.HS512;

  public String generateToken(String username, String secret) {

    return Jwts.builder()
        .setSubject(username)
        .signWith(ALGORITHM, getSigningKey(secret))
        .compact();
  }

  private Key getSigningKey(String secret) {
    byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secret);

    return new SecretKeySpec(apiKeySecretBytes, ALGORITHM.getJcaName());
  }

  public String getUsername(String token, String secret) {
    return Jwts.parser()
        .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
        .parseClaimsJws(token)
        .getBody()
        .getSubject();
  }
}
