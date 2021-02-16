import axios from "axios";
import AuthorizationService from "./authentication.service";

const KAFKA_PROXY_URL = "http://35.230.148.153:8080/api/match/enter";

class KafkaProxyService {

  findGame(user, stake) {
    console.log(user.username)
    return axios
    .post(KAFKA_PROXY_URL + "/" + stake, user.username,
        {
          headers: AuthorizationService.jwtHeader(),
        },
    )
    .then(successResponse => {
      return successResponse;
    });
  }
}

export default new KafkaProxyService();
