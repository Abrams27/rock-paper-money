import axios from "axios";
import AuthorizationService from "./authentication.service";


const DEBTS_SERVICE_URL = "http://localhost:2371/";

const RABBIT_SERVICE_URL = "http://localhost:7111/";

class DebtsService {
	getDebtorHistory(user, debtor) {
		return axios
			.get(DEBTS_SERVICE_URL + "history?requesterUsername=" +
				user.username + "&debtorUsername=" + debtor, {
				headers: AuthorizationService.jwtHeader(),
			})
			.then(successResponse => {
				return successResponse;
			});
	}

	getDebtorList(user) {
		return axios
			.get(DEBTS_SERVICE_URL + "debtor-list?requesterUsername=" + user.username, {
				headers: AuthorizationService.jwtHeader(),
			})
			.then(successResponse => {
				return successResponse;
			});
	}

	addDebtor(user, debtor) {
		return axios
			.post(DEBTS_SERVICE_URL + "debtor", {
					requesterUsername: user.username,
					debtorUsername: debtor,
				},
				{
					headers: AuthorizationService.jwtHeader(),
				},
			)
			.then(successResponse => {
				return successResponse;
			});
	}

	addDebt(user, debtor, amount, isRequesterOwned) {
		return axios
			.post(RABBIT_SERVICE_URL + "debt", {
				// headers: AuthorizationService.jwtHeader(),
				requesterUsername: user.username,
				debtorUsername: debtor,
				amount: amount,
				isRequesterOwned: isRequesterOwned,
			})
			.then(successResponse => {
				return successResponse;
			});
	}
}

export default new DebtsService();
