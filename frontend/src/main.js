import { createApp } from 'vue'
import Store from "@/scripts/store"
import App from './App.vue'
import router from "@/scripts/router"




createApp(App).use(Store).use(router).mount('#app')
