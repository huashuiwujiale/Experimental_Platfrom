import { createApp } from 'vue'
import App from './App.vue'
import router from './router/index'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 导入axios
import axios from "axios";
import { GlobalCmComponent } from "codemirror-editor-vue3";

const app = createApp(App)

app.use(router).mount('#app')
app.use(ElementPlus,{
  size:'30px'
}).use(GlobalCmComponent)
app.config.globalProperties.$axios = axios;