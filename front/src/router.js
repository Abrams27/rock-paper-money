import Vue from 'vue';
import Router from 'vue-router';
import LoginView from './views/LoginView.vue';
import RegisterView from './views/RegisterView.vue';

Vue.use(Router);

const router = new Router({
	mode: 'history',
	routes: [
		{
			path: '/player',
			component: () => import('./views/PlayerView.vue')
		},
		{
			path: '/login',
			component: LoginView
		},
		{
			path: '/register',
			component: RegisterView
		},
		{
			path: '/debtor/:username',
			name: 'debtor',
			// lazy-loaded
			component: () => import('./views/DebtorView.vue')
		},
	]
});

export default router;
