
import Vue from 'vue'
import './plugins/vuetify'
import App from './App.vue'
import index from './store/index'

Vue.config.productionTip = false

new Vue({
  store: index,
  render: h => h(App)
}).$mount('#app')
