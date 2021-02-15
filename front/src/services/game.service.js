import axios from "axios";
import AuthorizationService from "./authentication.service";


const GAME_API_URL = "http://localhost:8081/api/game-service/game/";

class GameService {

  getGameStatus(gameId){
    return axios
    .get(GAME_API_URL + gameId + "/info" , {
      headers: AuthorizationService.jwtHeader(),
    })
    .then(successResponse => {
      return successResponse;
    });
  }

  getGameResult(user, gameId){
    return axios
    .get(GAME_API_URL + gameId + "/result?username=" + user.username, {
      headers: AuthorizationService.jwtHeader(),
    })
    .then(successResponse => {
      return successResponse;
    });
  }

  enterGame(user, handSign, gameId) {
    return axios
    .post(GAME_API_URL + "enter", {
          gameId: gameId,
          playerUsername: user.username,
          handSign: handSign,
        },
        {
          headers: AuthorizationService.jwtHeader(),
        },
    )
    .then(successResponse => {
      return successResponse;
    });
  }

}

export default new GameService();
