import Vue from 'vue';
import Router from 'vue-router';
import LoginView from './views/LoginView.vue';
import RegisterView from './views/RegisterView.vue';
import GameView from "./views/GameView";

Vue.use(Router);

let router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/player',
      meta: {
        requireAuth: true
      },
      component: () => import('./views/PlayerView.vue')
    },
    {
      path: '/login',
      component: LoginView,
      meta: {
        hideForAuth: true
      }
    },
    {
      path: '/register',
      component: RegisterView,
      meta: {
        hideForAuth: true
      }
    },
		{
			path: '/game',
			component: GameView,
			meta: {
				requireAuth: true
			}
		},
    {
			path: '*',
			redirect: '/player',
		},
  ]
});

router.beforeEach((to, from, next) => {
  let user = JSON.parse(localStorage.getItem("user"));
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!user) {
      next({path: '/login'});
    } else {
      next();
    }

  } else if (to.matched.some(record => record.meta.hideForAuth)) {
    if (user) {
      next({path: '/player'});
    } else {
      next();
    }
  } else {
    next();
  }
});

router.replace({path: '*', redirect: '/player'}).then(

)

export default router;
