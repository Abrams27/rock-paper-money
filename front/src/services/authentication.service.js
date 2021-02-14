import axios from "axios";

const USER_SERVICE_URL = "http://localhost:1327/";

class AuthenticationService {
	jwtHeader() {
		let user = JSON.parse(localStorage.getItem("user"));
		if (user !== null && user.jwt !== null) {
			return {Authorization: "Bearer " + user.jwt};
		} else {
			return false;
		}
	}

	currentUser() {
		let user = JSON.parse(localStorage.getItem("user"));
		if (user !== null && user.jwt !== null) {
			return (user.username);
		} else {
			return undefined;
		}
	}

	login(user) {
		return axios
			.post(USER_SERVICE_URL + "authenticate", {
				username: user.username,
				password: user.password,
			})
			.then(successResponse => {
				if (successResponse.data.jwt) {
					localStorage.setItem("user", JSON.stringify(successResponse.data));
				}
			}).catch(() => {
				console.log("Login fail");
			});
	}

	logout() {
		localStorage.removeItem("user");
	}

	register(user) {
		return axios.post(USER_SERVICE_URL + "register", {
			username: user.username,
			password: user.password,
		}).catch(() => {
			console.log("Login fail");
		});
	}

	// authorize() {
	// 	return axios.post(USER_SERVICE_URL + "authorize", {
	// 		headers: this.jwtHeader(),
	// 	})
	// 		.then(successResponse => {
	// 			return true;
	// 		})
	// 		.catch(errorResponse => {
	// 			return false;
	// 		});
	// }
}

export default new AuthenticationService();
