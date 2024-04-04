import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import VueRouter from "vue-router"
import 'element-ui/lib/theme-chalk/index.css';
import './assets/gloabel.css';
import axios from 'axios'
import VueAxios from 'vue-axios'
import BaiduMap from 'vue-baidu-map'
import VueParticles from "vue-particles";
Vue.config.productionTip = false
Vue.use(ElementUI,{size:"small"});
Vue.use(VueRouter)
Vue.use(VueAxios,axios)
Vue.use(BaiduMap,{
  ak:''
})
new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
