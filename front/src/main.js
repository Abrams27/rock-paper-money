import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import * as VeeValidate from 'vee-validate';
import '@babel/polyfill';
import vuetify from './plugins/vuetify';

Vue.config.productionTip = false

const app = new Vue({
  router,
  store,
  VeeValidate,
  vuetify,
  render: h => h(App)
}).$mount('#app');

export default app;
