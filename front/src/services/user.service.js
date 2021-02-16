import axios from "axios";
import AuthorizationService from "./authentication.service";

const USER_API_URL = "http://35.230.148.153:8080/api/user";

class UserService {

  getGameHistory(user) {
    return axios
    .get(USER_API_URL + "/" + user.username + "/info", {
      headers: AuthorizationService.jwtHeader(),
    })
    .then(successResponse => {
      return successResponse;
    });
  }

  register(user) {
    return axios.post(USER_API_URL + "/" + "register", {
      username: user.username,
      password: user.password,
    }).catch(() => {
      console.log("Register fail");
    });
  }
}

export default new UserService();
