import AuthService from "../services/authentication.service";
import UserService from "../services/user.service";

const user = JSON.parse(localStorage.getItem("user"));

const initialState = user
	? {status: {loggedIn: true}, user: user}
	: {status: {loggedIn: false}, user: null};

export const auth = {
	namespaced: true,
	state: initialState,
	actions: {
		async login({commit}, user) {
			return await AuthService.login(user).then(
				user => {
					commit("loginSuccess");
					return Promise.resolve(user);
				},
				error => {
					commit("loginFailure");
					return Promise.reject(error);
				},
			);
		},
		logout({commit}) {
			AuthService.logout();
			commit("logout");
		},
		register({commit}, user) {
			return UserService.register(user).then(
				response => {
					commit("registerSuccess");
					return Promise.resolve(response.data);
				},
				error => {
					commit("registerFailure");
					return Promise.reject(error);
				},
			);
		},
	},
	mutations: {
		loginSuccess(state) {
			state.status.loggedIn = true;
			state.user = JSON.parse(localStorage.getItem("user"));
		},
		loginFailure(state) {
			state.status.loggedIn = false;
			state.user = null;
		},
		logout(state) {
			state.status.loggedIn = false;
			state.user = null;
		},
		registerSuccess(state) {
			state.status.loggedIn = false;
		},
		registerFailure(state) {
			state.status.loggedIn = false;
		},
	},
};
