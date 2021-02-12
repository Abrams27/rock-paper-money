package pl.uw.mim.jnp.rock.paper.money.app.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AuthorizationHeaderParser {

  private static final Integer BEARER_HEADER_PREFIX_SIZE = 7;

  public String getToken(String authorizationHeader) {
    return authorizationHeader.substring(BEARER_HEADER_PREFIX_SIZE);
  }
}
