import axios from "axios";

const AUTH_API_URL = "http://localhost:8082/api/user-service/auth/";

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
			.post(AUTH_API_URL + "login", {
				username: user.username,
				password: user.password,
			})
			.then(successResponse => {
				if (successResponse.data.token) {
					localStorage.setItem("user", JSON.stringify({
						username: user.username,
						jwt: successResponse.data.token,
					}));
				}
			}).catch(() => {
				console.log("Login fail");
			});
	}

	logout() {
		axios
		.get(AUTH_API_URL + "logout", {
			headers: this.jwtHeader(),
		}).then(() =>{
			localStorage.removeItem("user");
		});
	}
}

export default new AuthenticationService();
